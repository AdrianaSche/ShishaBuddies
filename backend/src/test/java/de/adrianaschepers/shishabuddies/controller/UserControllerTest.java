
package de.adrianaschepers.shishabuddies.controller;



import de.adrianaschepers.shishabuddies.api.Settings;
import de.adrianaschepers.shishabuddies.api.Setup;
import de.adrianaschepers.shishabuddies.api.User;
import de.adrianaschepers.shishabuddies.config.JwtConfig;
import de.adrianaschepers.shishabuddies.model.SettingsEntity;
import de.adrianaschepers.shishabuddies.model.SetupEntity;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import de.adrianaschepers.shishabuddies.repo.SetupEntityRepository;
import de.adrianaschepers.shishabuddies.repo.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cache.support.NullValue;
import org.springframework.http.*;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest(
        properties = "spring.profiles.active:h2",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    private String url(){
        return "http://localhost:" + port + "/user";
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SetupEntityRepository setupEntityRepository;

    @BeforeEach
    public void initializeDB(){

        List<SetupEntity> setupEntities1 = new LinkedList<>();
        SetupEntity setup1 = SetupEntity.builder()
                .title("Winter")
                .hookah("Aeon")
                .hookahHead("Phunnel")
                .tobacco("Cheesecake")
                .carbon("Power Coal")
                .setupCount(2L)
                .smokingDuration(120L)
                .comment("Sehr angenehm")
                .carbonTop("kaloud")
                .numOfSmokedHeads(2L)
                .accessories("Zange")
                .build();

        SetupEntity setup2 = SetupEntity.builder()
                .title("Summer")
                .hookah("Maklaud")
                .hookahHead("Phunnel")
                .tobacco("Erdbeer")
                .carbon("Power Coal")
                .setupCount(1L)
                .smokingDuration(100L)
                .comment("gerne wieder")
                .carbonTop("kaloud")
                .numOfSmokedHeads(1L)
                .accessories("Zange")
                .build();

        setupEntities1.add(setup1);
        setupEntities1.add(setup2);


        SettingsEntity settings1 = SettingsEntity.builder()
                .favTobacco("Traube-Minze")
                .favHookahHead("Oblako")
                .favHookah("Maklaud")
                .numberOfHookahHeads(10L)
                .numberOfHookahs(10L)
                .numberOfTobaccos(10L)
                .build();

        SettingsEntity settings2 = SettingsEntity.builder()
                .favTobacco("Apfel")
                .favHookahHead("Kopf")
                .favHookah("Maklaud1")
                .numberOfHookahHeads(20L)
                .numberOfHookahs(15L)
                .numberOfTobaccos(30L)
                .build();

        UserEntity user1 = UserEntity.builder()
               .userName("max")
                .firstName("Max")
                .lastName("Mustermann")
                .password("$2a$10$uGxuAgymiekt9qUIoYDK1ebzXtHZWwizOnJWEwgI97lwrC0G3EKoi")
                .email("max@mustermann.de")
                .role("user")
                .settings(settings1)
                .setups(setupEntities1)
                .build();

        user1.setSetups(setupEntities1);
        userRepository.saveAndFlush(user1);


        UserEntity user2 = UserEntity.builder()
                .userName("moritz")
                .firstName("Moritz")
                .lastName("Mustermann")
                .password("$2a$10$uGxuAgymiekt9qUIoYDK1ebzXtHZWwizOnJWEwgI97lwrC0G3EKoi")
                .email("moritz@mustermann.de")
                .role("user")
                .settings(settings2)
                .build();
        userRepository.saveAndFlush(user2);

        UserEntity user3 = UserEntity.builder()
                .userName("paul")
                .firstName("Paul")
                .lastName("Mustermann")
                .password("$2a$10$uGxuAgymiekt9qUIoYDK1ebzXtHZWwizOnJWEwgI97lwrC0G3EKoi")
                .email("paul@mustermann.de")
                .role("user")
                .build();
        userRepository.saveAndFlush(user3);



    }

    private HttpHeaders authorizedHeader(String username, String role){
        Map<String,Object> claims = new HashMap<>();
        claims.put("role", role);
        Instant now = Instant.now();
        Date iat = Date.from(now);
        Date exp = Date.from(now.plus(Duration.ofHours(jwtConfig.getExpiresAfterMinutes())));
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        return headers;
    }

    @AfterEach
    public void clearDB() {
        setupEntityRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("GET should return a list of all users in database (2 users)")
    public void testGetUsers(){

        String url = url() + "/all";
        HttpEntity<Void> httpEntity = new HttpEntity<>(authorizedHeader("max", "user"));
        ResponseEntity<User[]> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity,  User[].class);
        assertThat(response.getStatusCode(),is(HttpStatus.OK));
        assertThat(response.getBody(),is(notNullValue()));
        assertThat(response.getBody().length, is(3));

    }


    @Test
    @DisplayName("GET should return the settings of logged in User")
    public void testGetSettingsOfUser() {

        String url = url() + "/user-settings";
        HttpEntity<UserEntity> httpEntity = new HttpEntity<>(authorizedHeader("max", "user"));
        ResponseEntity<Settings> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Settings.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getFavTobacco(), is("Traube-Minze"));
        assertThat(response.getBody().getFavHookah(), is("Maklaud"));
    }


    @Test
    @DisplayName("GET should return no settings of logged in User")
    public void testNoSettingsAvailable() {

        String url = url() + "/user-settings";
        HttpEntity<UserEntity> httpEntity = new HttpEntity<>(authorizedHeader("paul", "user"));
        ResponseEntity<Settings> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Settings.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertNull(response.getBody().getNumberOfHookahs());

    }

    @Test
    @DisplayName("GET should return the setups of logged in User")
    public void testGetSetupsOfUser(){
        String url = url() + "/all-setups";
        HttpEntity<Void> httpEntity = new HttpEntity<>(authorizedHeader("max", "user"));
        ResponseEntity<Setup[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Setup[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(),is(notNullValue()));
        assertThat(response.getBody().length, is(2)); //ist 0
    }
}

