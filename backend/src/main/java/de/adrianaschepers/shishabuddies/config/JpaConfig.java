package de.adrianaschepers.shishabuddies.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"de.adrianaschepers.shishabuddies.model"})
@EnableJpaRepositories(basePackages = {"de.adrianaschepers.shishabuddies.repo"})
@EnableTransactionManagement
public class JpaConfig {
}
