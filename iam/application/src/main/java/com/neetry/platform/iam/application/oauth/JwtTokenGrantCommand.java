package com.neetry.platform.iam.application.oauth;


import com.neetry.platform.iam.domain.common.validator.BeanValidator;
import com.neetry.platform.iam.domain.user.Email;
import jakarta.validation.constraints.NotEmpty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class JwtTokenGrantCommand {

    @NotNull
    private final Email email;

    @NotEmpty
    private final String password;

    public JwtTokenGrantCommand(String email, String password) {
        this.email = Email.from(email);
        this.password = password;
        BeanValidator.validate(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final JwtTokenGrantCommand that)) return false;

        return new EqualsBuilder()
                .append(email, that.email)
                .append(password, that.password).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(email)
                .append(password).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email", email)
                .append("password", password)
                .toString();
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
