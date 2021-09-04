package de.adrianaschepers.shishabuddies.repo;

import de.adrianaschepers.shishabuddies.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity ,Long> {
}
