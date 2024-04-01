package com.neetry.platform.iam.infrastructure.inbound.rest.controller.user;


import com.neetry.platform.iam.application.user.UserCommandService;
import com.neetry.platform.iam.application.user.UserQueryService;
import com.neetry.platform.iam.domain.user.Role;
import com.neetry.platform.iam.domain.user.queries.UserInfoByIdQuery;
import com.neetry.platform.iam.domain.user.view.UserInfo;
import com.neetry.platform.iam.infrastructure.inbound.rest.config.security.CurrentUser;
import com.neetry.platform.iam.infrastructure.inbound.rest.model.request.RegisterUserRequest;
import com.neetry.platform.iam.infrastructure.inbound.rest.model.request.UpdateUserRequest;
import com.neetry.platform.iam.infrastructure.inbound.rest.model.response.UserInfoResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("oauth/public/accounts")
@Tag(name = "User")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;
    private final CurrentUser currentUser;

    @Autowired
    public UserController(
            UserQueryService userQueryService,
            UserCommandService userCommandService,
            CurrentUser currentUser) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
        this.currentUser = currentUser;
    }

    @PostMapping("user-register")
    public ResponseEntity<UserInfoResponse> register(@RequestBody @Valid RegisterUserRequest request) {
        Long id = userCommandService.handle(request.toCommand(Role.USER));
        UserInfo userInfo = userQueryService.getUserInfo(new UserInfoByIdQuery(id));
        UserInfoResponse response = UserInfoResponse.from(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("user-update")
    public ResponseEntity<UserInfoResponse> update(@RequestBody @Valid UpdateUserRequest request) {
        Long id = userCommandService.update(request.toCommand());
        UserInfo userInfo = userQueryService.getUserInfo(new UserInfoByIdQuery(id));
        UserInfoResponse response = UserInfoResponse.from(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("user-info")
    public ResponseEntity<UserInfoResponse> userInfo() {
        logger.info( "info v");
        Long currentUserId = currentUser.getJwtUserInfo().getUserId();
        UserInfo userInfo = userQueryService.getUserInfo(new UserInfoByIdQuery(currentUserId));
        UserInfoResponse response = UserInfoResponse.from(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
