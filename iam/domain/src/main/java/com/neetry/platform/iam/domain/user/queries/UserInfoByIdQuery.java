package com.neetry.platform.iam.domain.user.queries;

import com.neetry.platform.iam.domain.common.validator.BeanValidator;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserInfoByIdQuery {

    @NotNull
    private final Long userId;

    public UserInfoByIdQuery(@NotNull Long userId) {
        this.userId = userId;
        BeanValidator.validate(this);
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof UserInfoByIdQuery)) return false;

        final UserInfoByIdQuery that = (UserInfoByIdQuery) o;

        return new EqualsBuilder()
                .append(userId, that.userId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .toString();
    }
}
