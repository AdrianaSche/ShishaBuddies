package de.adrianaschepers.shishabuddies.service;

import de.adrianaschepers.shishabuddies.model.SettingsEntity;
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
    //private final SettingsRepository settingsRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository /*SettingsRepository settingsRepository*/) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        //this.settingsRepository = settingsRepository;
    }

    public Optional<UserEntity> find(String name){
        return userRepository.findByUserName(name);
    }

    public UserEntity createUser(UserEntity userEntity) {
        String userName = userEntity.getUserName();
        String email = userEntity.getEmail();
        if(!hasText(userName)){ //username blank
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

    public SettingsEntity getUserSettings(UserEntity authUser) {
        Optional<UserEntity> userOpt = userRepository.findByUserName(authUser.getUserName());
        if(userOpt.isPresent()) {
           return userOpt.get().getSettings();
        }
        throw new EntityNotFoundException("no settings available!");
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



}
