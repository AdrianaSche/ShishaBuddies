
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

import java.util.LinkedList;
import java.util.List;

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

    @GetMapping("all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<UserEntity> allEntities = userService.getAll();
        if(allEntities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<User> allUsers =map(allEntities);
        return ok(allUsers);

    }

    private List<User> map(List<UserEntity> allEntities) {
        List<User> users = new LinkedList<>();
        for (UserEntity userEntity:allEntities) {
           User user = map(userEntity);
           users.add(user);
        }
        return users;
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


