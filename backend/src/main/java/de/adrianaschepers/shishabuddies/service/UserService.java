package de.adrianaschepers.shishabuddies.service;

import de.adrianaschepers.shishabuddies.model.UserEntity;
import de.adrianaschepers.shishabuddies.repo.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
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
        if(!hasText(userName)){ //username blank
            throw new IllegalArgumentException("username required!");
        }

        //check, if username already exists in DB:
        checkUsernameExists(userName);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    private void checkUsernameExists(String userName) {
        Optional<UserEntity> existingUser = find(userName);
        if(existingUser.isPresent()){
            throw new EntityExistsException(String.format(
                    "user with username=%s already exists!",userName));
        }
    }


}
