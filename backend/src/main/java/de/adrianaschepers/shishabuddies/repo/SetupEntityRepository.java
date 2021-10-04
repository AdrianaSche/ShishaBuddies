package de.adrianaschepers.shishabuddies.repo;

import de.adrianaschepers.shishabuddies.model.SetupEntity;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetupEntityRepository extends JpaRepository<SetupEntity,Long> {

    Optional<SetupEntity> findByTitle(String title);
    //Optional<SetupEntity> findByUserEntity(UserEntity userEntity);
}
