
package de.adrianaschepers.shishabuddies.controller;

import de.adrianaschepers.shishabuddies.api.Settings;
import de.adrianaschepers.shishabuddies.api.User;
import de.adrianaschepers.shishabuddies.model.SettingsEntity;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import de.adrianaschepers.shishabuddies.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("settings")
   public ResponseEntity<Settings> createUserSettings(@RequestBody Settings settings, @AuthenticationPrincipal UserEntity authUser){
        SettingsEntity settingsEntity = map(settings);
        SettingsEntity createdSettingsEntity = userService.createSettings(settingsEntity,authUser);
        Settings createdSettings = map(createdSettingsEntity);
        return ok(createdSettings);
    }

    @PutMapping("update-settings")
    public ResponseEntity<Settings> updateSettings(@RequestBody Settings newSettings, @AuthenticationPrincipal UserEntity authUser){
        SettingsEntity newSettingsEntity = map(newSettings); //new settigns
        SettingsEntity updateSettingsEntity = userService.getUserSettings(authUser); //get old settings of user

       if(!updateSettingsEntity.getFavHookah().equals(newSettingsEntity.getFavHookah())){
           updateSettingsEntity.setFavHookah(newSettingsEntity.getFavHookah());
       }
       if(!updateSettingsEntity.getFavHookahHead().equals(newSettingsEntity.getFavHookahHead())){
           updateSettingsEntity.setFavHookahHead(newSettingsEntity.getFavHookahHead());
       }
       if(!updateSettingsEntity.getFavTobacco().equals(newSettingsEntity.getFavTobacco())){
           updateSettingsEntity.setFavTobacco(newSettingsEntity.getFavTobacco());
       }
       if(updateSettingsEntity.getNumberOfHookahs()!= newSettingsEntity.getNumberOfHookahs()){
           updateSettingsEntity.setNumberOfHookahs(newSettingsEntity.getNumberOfHookahs());
       }
       if(updateSettingsEntity.getNumberOfHookahHeads()!= newSettingsEntity.getNumberOfHookahHeads()){
           updateSettingsEntity.setNumberOfHookahHeads(newSettingsEntity.getNumberOfHookahHeads());
       }
       if(updateSettingsEntity.getNumberOfTobaccos()!= newSettingsEntity.getNumberOfTobaccos()){
           updateSettingsEntity.setNumberOfTobaccos(newSettingsEntity.getNumberOfTobaccos());
       }
       SettingsEntity updatedSettingsEnt= userService.createSettings(updateSettingsEntity,authUser);
       Settings updatedSettings = map(updatedSettingsEnt);

       return ok(updatedSettings);
    }

   @GetMapping("user-settings")
    public ResponseEntity<Settings> getUserSettings(@AuthenticationPrincipal UserEntity authUser){

            SettingsEntity settingsEntity = userService.getUserSettings(authUser);
            Settings settings = map(settingsEntity);
            return ok(settings);
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

    private SettingsEntity map(Settings settings) {
        return SettingsEntity.builder()
                .favHookah(settings.getFavHookah())
                .favHookahHead(settings.getFavHookahHead())
                .favTobacco(settings.getFavTobacco())
                .numberOfHookahHeads(settings.getNumberOfHookahHeads())
                .numberOfHookahs(settings.getNumberOfHookahs())
                .numberOfTobaccos(settings.getNumberOfTobaccos())
                .build();
    }

    private Settings map(SettingsEntity createdSettingsEntity) {
        return Settings.builder()
                .numberOfHookahs(createdSettingsEntity.getNumberOfHookahs())
                .numberOfTobaccos(createdSettingsEntity.getNumberOfTobaccos())
                .numberOfHookahHeads(createdSettingsEntity.getNumberOfHookahHeads())
                .favHookah(createdSettingsEntity.getFavHookah())
                .favHookahHead(createdSettingsEntity.getFavHookahHead())
                .favTobacco(createdSettingsEntity.getFavTobacco())
                .build();
    }

}


