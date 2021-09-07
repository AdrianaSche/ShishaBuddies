package de.adrianaschepers.shishabuddies.controller;

import de.adrianaschepers.shishabuddies.api.AccessToken;
import de.adrianaschepers.shishabuddies.api.Credentials;
import de.adrianaschepers.shishabuddies.api.User;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import de.adrianaschepers.shishabuddies.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService; //baut Token

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping("me")
    public ResponseEntity<User> getLoggedInUser(@AuthenticationPrincipal UserEntity user) {
        return ok(
                User.builder()
                        .name(user.getUserName())
                        .build()
        );

    }

    @PostMapping("access-token") //create Token
    public ResponseEntity<AccessToken> login(@RequestBody Credentials credentials) {  //nimmt credentials vom frontend entgegen
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword()

        );
        try {
            authenticationManager.authenticate(authToken);  //validiere credentials
           String token = jwtService.createToken(credentials.getUsername());
          return ok(new AccessToken(token)) ;
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
