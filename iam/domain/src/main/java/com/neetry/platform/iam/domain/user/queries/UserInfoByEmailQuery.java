package com.neetry.platform.iam.domain.user.queries;

import com.neetry.platform.iam.domain.common.validator.BeanValidator;
import com.neetry.platform.iam.domain.user.Email;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserInfoByEmailQuery {
    
    @NotNull
    private final Email email;

    public UserInfoByEmailQuery(Email email) {
        this.email = email;
        BeanValidator.validate(this);
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email", email)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof UserInfoByEmailQuery)) return false;

        final UserInfoByEmailQuery that = (UserInfoByEmailQuery) o;

        return new EqualsBuilder()
                .append(email, that.email)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(email)
                .toHashCode();
    }
}
