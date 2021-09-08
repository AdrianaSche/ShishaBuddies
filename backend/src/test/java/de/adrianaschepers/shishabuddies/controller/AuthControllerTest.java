package de.adrianaschepers.shishabuddies.controller;

import de.adrianaschepers.shishabuddies.SpringBootTests;
import de.adrianaschepers.shishabuddies.api.AccessToken;
import de.adrianaschepers.shishabuddies.api.Credentials;
import de.adrianaschepers.shishabuddies.config.JwtConfig;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import de.adrianaschepers.shishabuddies.repo.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static de.adrianaschepers.shishabuddies.controller.AuthController.ACCESS_TOKEN_URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest(

        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AuthControllerTest extends SpringBootTests {

    @LocalServerPort
    private int port;

    private String url() {
        return "http://localhost:" + port + ACCESS_TOKEN_URL ;
    }


    @AfterEach
    public void clearDb() {
        userRepository.deleteAll();
    }

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TestRestTemplate restTemplate;
    private final JwtConfig jwtConfig;


    @Autowired
    public AuthControllerTest(PasswordEncoder passwordEncoder, UserRepository userRepository, TestRestTemplate restTemplate, JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.jwtConfig = jwtConfig;
    }

    @Test
    public void createTokenSuccessful(){

        //GIVEN
        //create user in DB
        String username = "adriana";
        String password= "12345";
        String role = "user";

        String hashedPassword= passwordEncoder.encode(password);

        userRepository.saveAndFlush(
                UserEntity.builder()
                        .password(hashedPassword)
                        .userName(username)
                        .role(role).build()
        );


        //test login-Endpoint of AuthController:
        //send credentials!
        //(Im AuthController werden auf der login-Route die credentials entgegengenommen)
        Credentials credentials = Credentials.builder()
                .password(password)
                .username(username).build();

        //WHEN
        //do the request: Controller should send back the token
        ResponseEntity<AccessToken> response = restTemplate
                .exchange(url(), HttpMethod.POST,new HttpEntity<>(
                        credentials
                ),AccessToken.class);

        //THEN
        assertThat(response.getStatusCode(),is(HttpStatus.OK)); //check status code

        //check if username of credentials == sub of token:
        //1. get Token from response body:
        String token = response.getBody().getToken();

        //decode token and get the Claims:
        Claims claims = Jwts.parser() //build parser
                .setSigningKey(jwtConfig.getSecret()) //provide used secret
                .parseClaimsJws(token)  //parse the signed(!!) claims
                .getBody();     //extract payload from token


        String subject = claims.getSubject(); //username
        assertThat(subject,is(username));

    }
    @Test
    public void badCredentials(){

        //user not in db:
        Credentials credentials = Credentials.builder()
                .password("12345")
                .username("adriana")
                .build();

        ResponseEntity<AccessToken> response = restTemplate
                .exchange(url(), HttpMethod.POST,new HttpEntity<>(
                        credentials
                ),AccessToken.class);

        assertThat(response.getStatusCode(),is(HttpStatus.UNAUTHORIZED));
        assertThat(response.getBody(),is(nullValue())); //no token created-> body must be empty!

    }

    @Test
    public void NoCredentials(){

        //user not in db:
        Credentials credentials = null;

        ResponseEntity<AccessToken> response = restTemplate
                .exchange(url(), HttpMethod.POST,new HttpEntity<>(
                        credentials
                ),AccessToken.class);

        assertThat(response.getStatusCode(),is(HttpStatus.BAD_REQUEST));
       // assertThat(response.getBody(),is(nullValue()));

    }
}
