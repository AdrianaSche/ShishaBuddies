
package de.adrianaschepers.shishabuddies.controller;

import de.adrianaschepers.shishabuddies.api.Settings;
import de.adrianaschepers.shishabuddies.api.Setup;
import de.adrianaschepers.shishabuddies.api.User;
import de.adrianaschepers.shishabuddies.model.SettingsEntity;
import de.adrianaschepers.shishabuddies.model.SetupEntity;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import de.adrianaschepers.shishabuddies.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

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
        SettingsEntity createdSettingsEntity = userService.saveSettings(settingsEntity,authUser);
        Settings createdSettings = map(createdSettingsEntity);
        return ok(createdSettings);
    }

    @PostMapping("create-setup")
    public ResponseEntity<Setup> createSetup(@RequestBody Setup setup, @AuthenticationPrincipal UserEntity authUser){
        SetupEntity setupEntity = map(setup);
        SetupEntity createdSetupEntity = userService.saveSetup(setupEntity,authUser);
        Setup createdSetup = map(createdSetupEntity);
        return ok(createdSetup);
    }

    @GetMapping("all-setups")
    public ResponseEntity<List<Setup>> getAllSetups(@AuthenticationPrincipal UserEntity authUser){
        List<SetupEntity> setups = userService.getAllSetups(authUser);
        if(setups.isEmpty()){
            return notFound().build();
        }
        return ok(mapSetup(setups));
    }

    @GetMapping("setup/details/{title}")
    public ResponseEntity<Setup> getSetupByTitle(@PathVariable String title,@AuthenticationPrincipal UserEntity authUser){
        SetupEntity setupEntity = userService.getSetupByTitle(authUser,title);
        Setup searchedSetup = map(setupEntity);
        return ok(searchedSetup);

    }

    @GetMapping("{id}")
    public ResponseEntity<Setup> getSetupById(@PathVariable("id") Long id,@AuthenticationPrincipal UserEntity authUser){
        SetupEntity setupEntity = userService.getSetupById(id,authUser);

        if(setupEntity==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Setup setup = map(setupEntity);
        return ok(setup);
    }



    @PutMapping("update-settings")
    public ResponseEntity<Settings> updateSettings(@RequestBody Settings newSettings, @AuthenticationPrincipal UserEntity authUser){
        SettingsEntity newSettingsEntity = map(newSettings);
        SettingsEntity updateSettingsEntity = userService.getUserSettings(authUser);

       if(!updateSettingsEntity.getFavHookah().equals(newSettingsEntity.getFavHookah())){
           updateSettingsEntity.setFavHookah(newSettingsEntity.getFavHookah());
       }
       if(!updateSettingsEntity.getFavHookahHead().equals(newSettingsEntity.getFavHookahHead())){
           updateSettingsEntity.setFavHookahHead(newSettingsEntity.getFavHookahHead());
       }
       if(!updateSettingsEntity.getFavTobacco().equals(newSettingsEntity.getFavTobacco())){
           updateSettingsEntity.setFavTobacco(newSettingsEntity.getFavTobacco());
       }
       if(!updateSettingsEntity.getNumberOfHookahs().equals(newSettingsEntity.getNumberOfHookahs())){
           updateSettingsEntity.setNumberOfHookahs(newSettingsEntity.getNumberOfHookahs());
       }
       if(!updateSettingsEntity.getNumberOfTobaccos().equals(newSettingsEntity.getNumberOfTobaccos())){
           updateSettingsEntity.setNumberOfTobaccos(newSettingsEntity.getNumberOfTobaccos());
       }
       SettingsEntity updatedSettingsEnt= userService.saveSettings(updateSettingsEntity,authUser);
       Settings updatedSettings = map(updatedSettingsEnt);

       return ok(updatedSettings);
    }

   @GetMapping("user-settings")
    public ResponseEntity<Settings> getUserSettings(@AuthenticationPrincipal UserEntity authUser){

            SettingsEntity settingsEntity = userService.getUserSettings(authUser);
            if(settingsEntity==null){
                return ok(Settings.builder().build());
            }
            Settings settings = map(settingsEntity);
            return ok(settings);
    }

    @PutMapping("update-setup/{title}")
    public ResponseEntity<Setup> updateSetup(@RequestBody Setup setup,@PathVariable String title, @AuthenticationPrincipal UserEntity authUser){

        SetupEntity newSetup = map(setup);
        newSetup.setTitle(title);

        SetupEntity currentSetup = userService.getSetupByTitle(authUser, title);

        if(!currentSetup.getComment().equals(newSetup.getComment())){
            currentSetup.setComment(newSetup.getComment());
        }

        currentSetup.setNumOfSmokedHeads(currentSetup.getNumOfSmokedHeads()+ newSetup.getNumOfSmokedHeads());
        currentSetup.setSmokingDuration(currentSetup.getSmokingDuration() + newSetup.getSmokingDuration());
        currentSetup.setSetupCount(currentSetup.getSetupCount()+ newSetup.getSetupCount());
        SetupEntity updatedSetupEntity= userService.updateSetup(currentSetup);
        Setup updatedSetup = map(updatedSetupEntity);

        return ok(updatedSetup);
    }


    @GetMapping
    public ResponseEntity<Long> getTotalSmokingDurationOfUser(@AuthenticationPrincipal UserEntity authUser){
        Long currentSmokingDuration =0L;
        List<SetupEntity> setupEntities = userService.getAllSetups(authUser);
        for (SetupEntity setupEntity:setupEntities) {
            currentSmokingDuration +=setupEntity.getSmokingDuration();
        }
        return ok(currentSmokingDuration);

    }

    @GetMapping("/heads")
    public ResponseEntity<Long> getTotalCountOfHead (@AuthenticationPrincipal UserEntity authUser){
        Long currentNumOfHeads =0L;
        List<SetupEntity> setupEntities = userService.getAllSetups(authUser);
        for (SetupEntity setupEntity:setupEntities) {
            currentNumOfHeads +=setupEntity.getNumOfSmokedHeads();
        }
        return ok(currentNumOfHeads);

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

    @DeleteMapping("delete-setup/{title}")
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "Setup not found")
    })
    public ResponseEntity<Setup> deleteSetup(@PathVariable String title,@AuthenticationPrincipal UserEntity authUser){
        Optional<SetupEntity> setupEntityOptional = userService.deleteSetupByTitle(authUser, title);
        if(setupEntityOptional.isPresent()){
            SetupEntity setupEntity = setupEntityOptional.get();
            Setup setup = map(setupEntity);
            return ok(setup);
        }
        return notFound().build();
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



    private List<Setup> mapSetup(List<SetupEntity> setupEntities){
        List<Setup> setups = new LinkedList<>();
        for (SetupEntity setupEntity: setupEntities) {
            setups.add(map(setupEntity));
        }
        return setups;

    }

    private Setup map(SetupEntity createdSetupEntity) {
        return Setup.builder()
                .accessories(createdSetupEntity.getAccessories())
                .carbon(createdSetupEntity.getCarbon())
                .setupCount(createdSetupEntity.getSetupCount())
                .carbonTop(createdSetupEntity.getCarbonTop())
                .comment(createdSetupEntity.getComment())
                .hookahHead(createdSetupEntity.getHookahHead())
                .hookah(createdSetupEntity.getHookah())
                .numOfSmokedHeads(createdSetupEntity.getNumOfSmokedHeads())
                .smokingDuration(createdSetupEntity.getSmokingDuration())
                .title(createdSetupEntity.getTitle())
                .tobacco(createdSetupEntity.getTobacco())
                .build();
    }

    private SetupEntity map(Setup setup) {
        return SetupEntity.builder()
                .accessories(setup.getAccessories())
                .carbon(setup.getCarbon())
                .numOfSmokedHeads(setup.getNumOfSmokedHeads())
                .carbonTop(setup.getCarbonTop())
                .setupCount(setup.getSetupCount())
                .smokingDuration(setup.getSmokingDuration())
                .comment(setup.getComment())
                .hookah(setup.getHookah())
                .hookahHead(setup.getHookahHead())
                .title(setup.getTitle())
                .tobacco(setup.getTobacco())
                .build();
    }

}


