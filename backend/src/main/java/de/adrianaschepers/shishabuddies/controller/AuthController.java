package de.adrianaschepers.shishabuddies.controller;

import de.adrianaschepers.shishabuddies.api.AccessToken;
import de.adrianaschepers.shishabuddies.api.Credentials;
import de.adrianaschepers.shishabuddies.api.User;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import de.adrianaschepers.shishabuddies.service.JwtService;
import de.adrianaschepers.shishabuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService; //baut Token
    private final UserService userService;
    public static final String ACCESS_TOKEN_URL = "/auth/access-token";

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping("me")
    public ResponseEntity<User> getLoggedInUser(@AuthenticationPrincipal UserEntity user) {
        return ok(
                User.builder()
                       .lastName(user.getLastName())
                        .firstName(user.getFirstName())
                        .userName(user.getUserName())
                        .build()
        );

    }

    @PostMapping("access-token") //create Token
    public ResponseEntity<AccessToken> getAccessToken(@RequestBody Credentials credentials) {//nimmt credentials vom frontend entgegen
        String username = credentials.getUserName();
        String password = credentials.getPassword();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,password);

        try {
            authenticationManager.authenticate(authToken);  //validiere credentials
            UserEntity user = userService.find(username).orElseThrow();
           String token = jwtService.createToken(user);

           AccessToken accessToken = new AccessToken(token);
          return ok(accessToken) ;
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
