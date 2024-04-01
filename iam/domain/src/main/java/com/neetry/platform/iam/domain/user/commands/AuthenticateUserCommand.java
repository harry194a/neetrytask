package com.neetry.platform.iam.domain.user.commands;

import com.neetry.platform.iam.domain.common.validator.BeanValidator;
import com.neetry.platform.iam.domain.user.Email;
import jakarta.validation.constraints.NotEmpty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class AuthenticateUserCommand {
    
    @NotNull
    private final Email email;

    @NotEmpty
    private final String password;

    public @NotNull Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final AuthenticateUserCommand that)) return false;

        return new EqualsBuilder()
                .append(getEmail(), that.getEmail())
                .append(getPassword(), that.getPassword()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getEmail())
                .append(getPassword()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email", email)
                .append("password", password)
                .toString();
    }

    public AuthenticateUserCommand(@NotNull final Email email,
                                   final String password) {
        this.email = email;
        this.password = password;
        BeanValidator.validate(this);

    }
}
