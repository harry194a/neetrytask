package com.neetry.platform.iam.domain.user;

import com.neetry.platform.iam.domain.user.exception.UserNotFoundException;

import java.util.Optional;

public interface UserRepository {
   
    Optional<User> findById(Long id);

    Optional<User> findByEmail(Email email);

    User getById(Long id) throws UserNotFoundException;

    void deleteById(Long id) throws UserNotFoundException;


    User getByEmail(Email email) throws UserNotFoundException;

    User save(User user);
}
