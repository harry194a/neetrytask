package com.neetry.platform.iam.infrastructure.outbound.persistence.repositories.user.impl;

import com.neetry.platform.iam.domain.user.Email;
import com.neetry.platform.iam.domain.user.User;
import com.neetry.platform.iam.domain.user.UserRepository;
import com.neetry.platform.iam.domain.user.view.UserInfo;
import com.neetry.platform.iam.domain.user.view.UserQueryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserQueryRepositoryJpa implements UserQueryRepository {

    private final UserRepository userRepository;

    public UserQueryRepositoryJpa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<UserInfo> findUserById(final Long id) {
        return userRepository.findById(id).map(this::mapToUserInfo);
    }

    @Override
    public Optional<UserInfo> findUserByEmail(final Email email) {
        return userRepository.findByEmail(email).map(this::mapToUserInfo);
    }

    private UserInfo mapToUserInfo(User user) {
        if (user == null) {
            return null;
        }

        return new UserInfo(
                user.getId(),
                user.getEmail().toString(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhone(),
                user.getAddress(),
                user.getRole()
        );
    }
}
