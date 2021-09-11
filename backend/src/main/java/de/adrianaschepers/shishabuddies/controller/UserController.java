
package de.adrianaschepers.shishabuddies.controller;

import de.adrianaschepers.shishabuddies.api.User;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import de.adrianaschepers.shishabuddies.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@Getter
@Setter
@RequestMapping("user")
public class UserController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("new-user")
    @ApiResponses(value = {
            @ApiResponse(code = SC_BAD_REQUEST, message = "Unable to create User with blank name!" ),
            @ApiResponse(code = SC_CONFLICT, message = "Unable to create User, User already exists!")
    })
    public ResponseEntity<User> createUser(@RequestBody User user){

        UserEntity userEntity = map(user);
        userEntity.setPassword(user.getPassword());
        userEntity.setRole("user");
        UserEntity createdUserEntity = userService.createUser(userEntity);
        User createdUser = map(createdUserEntity);
        createdUser.setPassword(user.getPassword());

        return ok(createdUser);
    }

    private User map(UserEntity userEntity) {
        return User.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .userName(userEntity.getUserName())
                .email(userEntity.getEmail())
                .build();
    }

    private UserEntity map(User user) {
        return UserEntity.builder()
                .userName(user.getUserName())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .build();
    }

}


