package com.neetry.platform.iam.application.user;

import com.neetry.platform.iam.domain.user.exception.UserNotFoundException;
import com.neetry.platform.iam.domain.user.queries.UserInfoByIdQuery;
import com.neetry.platform.iam.domain.user.view.UserInfo;
import com.neetry.platform.iam.domain.user.view.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserQueryService {

    private final UserQueryRepository userQueryRepository;

    @Autowired
    public UserQueryService(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    @Transactional(readOnly = true)
    public UserInfo getUserInfo(UserInfoByIdQuery query) throws UserNotFoundException {
        return userQueryRepository.findUserById(query.getUserId())
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found by query - %s", query)));
    }
}
