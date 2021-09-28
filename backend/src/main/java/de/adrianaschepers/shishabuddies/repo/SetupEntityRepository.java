package de.adrianaschepers.shishabuddies.repo;

import de.adrianaschepers.shishabuddies.model.SetupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetupEntityRepository extends JpaRepository<SetupEntity,Long> {

    Optional<SetupEntity> findByTitle(String title);
}
