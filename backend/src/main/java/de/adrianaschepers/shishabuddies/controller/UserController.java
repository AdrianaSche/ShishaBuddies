package de.adrianaschepers.shishabuddies.controller;


import de.adrianaschepers.shishabuddies.api.User;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import de.adrianaschepers.shishabuddies.service.UserService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

import static de.adrianaschepers.shishabuddies.controller.UserController.USER_TAG;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;

@Tag(name = USER_TAG, description = "this controller provides swagger output for testing purpose")
@Api(
        tags = USER_TAG
)
@ApiIgnore
@CrossOrigin
@RequestMapping("/")
@RestController
public class UserController {


    public static final String USER_TAG = "User";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //find user by Name to check , if i can get one user from the database
    @GetMapping("/{name}")
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "User not found")
    })
    /*public ResponseEntity<UserEntity> find(@PathVariable String name){
        Optional<UserEntity> userEntityOptional = userService.find(name);
        UserEntity userEntity = userEntityOptional.get();
        return ok(userEntity);
    }*/

    public ResponseEntity<User> find (@PathVariable String name){
        Optional<UserEntity> userEntityOptional = userService.find(name);
        UserEntity userEntity = userEntityOptional.get();
        User user = map(userEntity);
        return ok(user);

    }

    private User map(UserEntity userEntity) {
        return User.builder()
                .name(userEntity.getUserName())
                .build();
    }

}
