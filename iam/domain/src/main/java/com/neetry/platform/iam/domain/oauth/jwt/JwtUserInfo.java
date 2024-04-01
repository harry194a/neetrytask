package com.neetry.platform.iam.domain.oauth.jwt;

import com.neetry.platform.iam.domain.user.view.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class JwtUserInfo {

    @NotNull
    private final Long userId;
    @NotEmpty
    @Email
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String role;

    public JwtUserInfo(Long userId, String email, String firstName, String lastName, String role) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }


    public static JwtUserInfo of(UserInfo userInfo) {
        return new JwtUserInfo(userInfo.getUserId(),
                userInfo.getEmail(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getRole().toString());
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("role", role)
                .toString();
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }
}
