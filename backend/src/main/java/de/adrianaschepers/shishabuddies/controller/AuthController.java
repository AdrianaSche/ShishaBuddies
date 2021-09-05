package de.adrianaschepers.shishabuddies.controller;

import de.adrianaschepers.shishabuddies.api.User;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class AuthController {

    @GetMapping("me")
    public ResponseEntity<User> getLoggedInUser(Principal principal) {
        String username = principal.getName();
        return ResponseEntity.ok(
                User.builder()
                        .name(username)
                        .build()
        );

    }
}
