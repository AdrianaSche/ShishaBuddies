package de.adrianaschepers.shishabuddies.repo;

import de.adrianaschepers.shishabuddies.model.SettingsEntity;
import de.adrianaschepers.shishabuddies.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettingsRepository extends JpaRepository<SettingsEntity,Long> {
    Optional<SettingsEntity> findSettingsEntityByUser(UserEntity userEntity);

}
