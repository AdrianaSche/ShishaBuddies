package de.adrianaschepers.shishabuddies.service;

import de.adrianaschepers.shishabuddies.api.Setup;
import de.adrianaschepers.shishabuddies.model.SettingsEntity;
import de.adrianaschepers.shishabuddies.model.SetupEntity;
import de.adrianaschepers.shishabuddies.model.UserEntity;
//import de.adrianaschepers.shishabuddies.repo.SettingsRepository;
import de.adrianaschepers.shishabuddies.repo.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
@Getter
@Setter
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final  UserRepository userRepository;


    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> find(String name){
        return userRepository.findByUserName(name);
    }

    public UserEntity createUser(UserEntity userEntity) {
        String userName = userEntity.getUserName();
        String email = userEntity.getEmail();
        if(!hasText(userName)){
            throw new IllegalArgumentException("username required!");
        }
        if(!hasText(email)){
            throw new IllegalArgumentException("email required!");
        }
        checkUsernameExists(userName);
        checkEmailExists(email);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }



    public SettingsEntity getUserSettings(UserEntity authUser){
        Optional<UserEntity> authUserOpt= userRepository.findByUserName(authUser.getUserName());
        if(authUserOpt.isPresent()){
            SettingsEntity settingsEntity = authUserOpt.get().getSettings();
            if(settingsEntity!= null){
                return settingsEntity;
            }
        }
       // throw new EntityNotFoundException("no settings available!");
        return SettingsEntity.builder().build();
    }



    public SettingsEntity saveSettings(SettingsEntity settingsEntity, UserEntity authUser) {
        Optional<UserEntity> userEntityOptional=userRepository.findByUserName(authUser.getUserName());
        if(userEntityOptional.isPresent()){
            settingsEntity.setUser(userEntityOptional.get());
            userEntityOptional.get().setSettings(settingsEntity);
             userRepository.saveAndFlush(userEntityOptional.get());
             return settingsEntity;
        }
        throw new EntityNotFoundException("user not in db");
    }

    public SetupEntity saveSetup(SetupEntity setupEntity, UserEntity authUser) {
        Optional<UserEntity> authUserOptional = userRepository.findByUserName(authUser.getUserName());
        if(authUserOptional.isPresent()){
           UserEntity user= authUserOptional.get();
            setupEntity.setUserEntity(user);
            checkIfTitleExists(setupEntity,user.getSetups());
            user.getSetups().add(setupEntity);
            userRepository.saveAndFlush(user);
            return setupEntity;
        }
        throw new EntityNotFoundException("user not in db");
    }



    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }


    private void checkEmailExists(String email) {
        List<UserEntity> allEntities = userRepository.findAll();
        for (UserEntity userEntity:allEntities) {
            if(userEntity.getEmail().equals(email)){
                throw new IllegalArgumentException
                        (String.format("email=%s already exists!",email));
            }
        }

    }

    private void checkUsernameExists(String userName) {
        Optional<UserEntity> existingUser = find(userName);
        if(existingUser.isPresent()){
            throw new EntityExistsException(String.format(
                    "user with username=%s already exists!",userName));
        }
    }


    public List<SetupEntity> getAllSetups(UserEntity authUser) {
       UserEntity user= userRepository.findByUserName(authUser.getUserName()).get();
       return user.getSetups();
    }

    private void checkIfTitleExists(SetupEntity setupEntity,List<SetupEntity> setupEntities){
        for (SetupEntity setupEnt:setupEntities) {
            if(setupEntity.getTitle().equals(setupEnt.getTitle())){
                throw new EntityExistsException(String.format("title=%s already taken!",setupEntity.getTitle()));
            }
        }

    }

    public SetupEntity getSetupByTitle(UserEntity authUser, String title) {
        UserEntity userEntity = userRepository.findByUserName(authUser.getUserName()).get();
        List<SetupEntity> setupEntities=userEntity.getSetups();
        for (SetupEntity setupEntity:setupEntities) {
            if(setupEntity.getTitle().equals(title)){
                return setupEntity;
            }
        }
        throw new EntityNotFoundException(String.format("no setup with title=%s !",title ));
    }

    public Optional<SetupEntity> getSetupById(String id,UserEntity authUser) {
        UserEntity userEntity = userRepository.findByUserName(authUser.getUserName()).get();
        List<SetupEntity> setupEntities = userEntity.getSetups();
        for (SetupEntity setupEntity:setupEntities) {
            if(setupEntity.getId().equals(id)){
                return Optional.of(setupEntity);
            }
        }
        throw new EntityNotFoundException(String.format("no setup with id=%s !",id ));
    }
}
