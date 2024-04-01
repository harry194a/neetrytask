package com.neetry.platform.iam.domain.user.view;

import com.neetry.platform.iam.domain.user.Email;

import java.util.Optional;

public interface UserQueryRepository {

    Optional<UserInfo> findUserById(Long id);

    Optional<UserInfo> findUserByEmail(Email email);
}
