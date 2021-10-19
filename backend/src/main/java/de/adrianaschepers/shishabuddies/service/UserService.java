package de.adrianaschepers.shishabuddies.service;

import de.adrianaschepers.shishabuddies.model.SettingsEntity;
import de.adrianaschepers.shishabuddies.model.SetupEntity;
import de.adrianaschepers.shishabuddies.model.UserEntity;
//import de.adrianaschepers.shishabuddies.repo.SettingsRepository;
import de.adrianaschepers.shishabuddies.repo.SetupEntityRepository;
import de.adrianaschepers.shishabuddies.repo.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
@Getter
@Setter
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final  UserRepository userRepository;
    private final SetupEntityRepository setupEntityRepository;


    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, SetupEntityRepository setupEntityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.setupEntityRepository = setupEntityRepository;
    }


    public Optional<UserEntity> find(String name){
        return userRepository.findByUserName(name);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
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
            user.getSetups().add(setupEntity);
            userRepository.saveAndFlush(user);
            return setupEntity;
        }
        throw new EntityNotFoundException("user not in db");
    }

    public SetupEntity updateSetup(SetupEntity setupEntity){
        return setupEntityRepository.save(setupEntity);
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

    public List<SetupEntity> getAllSetups(UserEntity authUser) {
       UserEntity user= userRepository.findByUserName(authUser.getUserName()).get();
       return user.getSetups();
    }



    public SetupEntity getSetupByTitle(UserEntity authUser, String title) {
        UserEntity userEntity = userRepository.findByUserName(authUser.getUserName()).get();
        List<SetupEntity> setupEntities=userEntity.getSetups();
        if(setupEntities == null){
            throw new IllegalArgumentException("no setups available");
        }
        for (SetupEntity setupEntity:setupEntities) {
            if(setupEntity.getTitle().equals(title)){
                return setupEntity;
            }
        }

        return SetupEntity.builder().build();
    }

    public SetupEntity getSetupById(Long id,UserEntity authUser) {
        UserEntity userEntity = userRepository.findByUserName(authUser.getUserName()).get();
        List<SetupEntity> setupEntities = userEntity.getSetups();
        for (SetupEntity setupEntity:setupEntities) {
            if(setupEntity.getId().equals(id)){
                return setupEntity;
            }
        }
        throw new EntityNotFoundException(String.format("no setup with id=%s !",id ));
    }

    private void checkUsernameExists(String userName) {
        Optional<UserEntity> existingUser = find(userName);
        if(existingUser.isPresent()){
            throw new EntityExistsException(String.format(
                    "user with username=%s already exists!",userName));
        }
    }

    public Optional<SetupEntity> deleteSetupByTitle(UserEntity authUser, String title) {
        Optional<SetupEntity> setupEntityOptional = setupEntityRepository.findByTitle(title);
        SetupEntity setupEntity =new SetupEntity();
        if(setupEntityOptional.isPresent()){
             setupEntity = setupEntityOptional.get();
        }
        Optional<UserEntity> userEntityOptional = userRepository.findByUserName(authUser.getUserName());
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            userEntity.removeSetupEntity(setupEntity);
            userRepository.save(userEntity);
        }

        return setupEntityOptional;
    }
}
