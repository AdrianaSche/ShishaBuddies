package de.adrianaschepers.shishabuddies.repo;

import de.adrianaschepers.shishabuddies.model.SettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<SettingsEntity,Long> {
}
