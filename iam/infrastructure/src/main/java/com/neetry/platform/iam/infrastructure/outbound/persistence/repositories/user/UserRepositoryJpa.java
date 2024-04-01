package com.neetry.platform.iam.infrastructure.outbound.persistence.repositories.user;

import com.neetry.platform.iam.domain.user.User;
import com.neetry.platform.iam.domain.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<User, Long>, UserRepository {
    
}
