package com.neetry.platform.iam.infrastructure.inbound.rest.model.request;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class GrantOauthTokenRequest {

    @NotEmpty
    @Email
    private String userName;

    @NotEmpty
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userName", userName)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final GrantOauthTokenRequest that)) return false;

        return new EqualsBuilder()
                .append(userName, that.userName)
                .append(password, that.password)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userName)
                .append(password)
                .toHashCode();
    }
}
