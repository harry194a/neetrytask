package com.neetry.platform.iam.infrastructure.outbound.persistence.repositories.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.neetry.platform.iam"})
@EntityScan(basePackages = {"com.neetry.platform.iam"})
public class DataConfig {
}
