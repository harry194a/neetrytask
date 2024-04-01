package com.neetry.platform.iam.infrastructure.inbound.rest.controller.admin;


import com.neetry.platform.iam.application.user.UserCommandService;
import com.neetry.platform.iam.application.user.UserQueryService;
import com.neetry.platform.iam.domain.user.Role;
import com.neetry.platform.iam.domain.user.queries.UserInfoByIdQuery;
import com.neetry.platform.iam.domain.user.view.UserInfo;
import com.neetry.platform.iam.infrastructure.inbound.rest.model.request.RegisterUserRequest;
import com.neetry.platform.iam.infrastructure.inbound.rest.model.response.UserInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/private/accounts")
@Tag(name = "Admin")
public class AccountAdminController {
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    @Autowired
    public AccountAdminController(
            UserQueryService userQueryService,
            UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @PostMapping("admin-register")
    @Operation(summary = "Register admin account")
    public ResponseEntity<UserInfoResponse> register(@RequestBody @Valid RegisterUserRequest request) {
        Long id = userCommandService.handle(request.toCommand(Role.ADMIN));
        UserInfo userInfo = userQueryService.getUserInfo(new UserInfoByIdQuery(id));
        UserInfoResponse response = UserInfoResponse.from(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("admin-delete/{id}")
    @Operation(summary = "Delete admin account")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        Long deletedId = userCommandService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedId);
    }
}
