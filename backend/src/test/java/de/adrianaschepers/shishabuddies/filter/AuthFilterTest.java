package de.adrianaschepers.shishabuddies.filter;

import de.adrianaschepers.shishabuddies.api.User;
import de.adrianaschepers.shishabuddies.config.JwtConfig;
import de.adrianaschepers.shishabuddies.repo.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthFilterTest {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtConfig jwtConfig;
    private final TestRestTemplate restTemplate;

    @Autowired
    public AuthFilterTest(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtConfig jwtConfig, TestRestTemplate restTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtConfig = jwtConfig;
        this.restTemplate = restTemplate;
    }


    @LocalServerPort
    private int port;

    private String url(){
        return "http://localhost:"+ port + "/auth/me";
    }



    @Test
    public void login(){  //do we get a valid Token?

        //1.build signed JWT
        String username = "adriana";
        Instant now = Instant.now();
        Date iat = Date.from(now);
        Date exp = Date.from(now.plus(Duration.ofMinutes(jwtConfig.getExpiresAfterMinutes())));
        String token = Jwts.builder()
                .setClaims(new HashMap<>(
                        Map.of("role", "user")
                ))
                .setSubject(username)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256,jwtConfig.getSecret()).compact();
        //2.create header: Authorization "Bearer: token"
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        //When
        //3.send header:
        ResponseEntity<User> response = restTemplate
                .exchange(url(), HttpMethod.GET,new HttpEntity<>(headers),User.class);

        //then
        assertThat(response.getStatusCode(),is(HttpStatus.OK));
        assertThat(response.getBody().getUserName(),is(username));

    }

    @Test
    public void tokenHasWrongSignature(){

        //1.build signed JWT
        String username = "adriana";
        Instant now = Instant.now();
        Date iat = Date.from(now);
        Date exp = Date.from(now.plus(Duration.ofMinutes(jwtConfig.getExpiresAfterMinutes())));
        String wrongSecret = jwtConfig.getSecret() + "wrong-secret";
        String token = Jwts.builder()
                .setClaims(new HashMap<>(
                        Map.of("role", "user")
                ))
                .setSubject(username)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256,wrongSecret).compact();
        //2.create header: Authorization "Bearer: token"
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        //When
        //3.send header:
        ResponseEntity<User> response = restTemplate
                .exchange(url(), HttpMethod.GET,new HttpEntity<>(headers),User.class);

        assertThat(response.getStatusCode(),is(HttpStatus.FORBIDDEN));

    }

    @Test
    public void tokenExpired(){
        //1.build signed JWT
        String username = "adriana";
        Instant now = Instant.now().minus(Duration.ofMinutes(jwtConfig.getExpiresAfterMinutes()*2));
        Date iat = Date.from(now);
        Date exp = Date.from(now.plus(Duration.ofMinutes(jwtConfig.getExpiresAfterMinutes())));
        String token = Jwts.builder()
                .setClaims(new HashMap<>(
                        Map.of("role", "user")
                ))
                .setSubject(username)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256,jwtConfig.getSecret()).compact();
        //2.create header: Authorization "Bearer: token"
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        //When
        //3.send header:
        ResponseEntity<User> response = restTemplate
                .exchange(url(), HttpMethod.GET,new HttpEntity<>(headers),User.class);

        assertThat(response.getStatusCode(),is(HttpStatus.FORBIDDEN));


    }
}
