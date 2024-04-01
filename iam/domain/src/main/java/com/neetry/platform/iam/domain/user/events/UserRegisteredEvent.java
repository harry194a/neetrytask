package com.neetry.platform.iam.domain.user.events;

import com.neetry.platform.iam.domain.common.DomainEvent;
import com.neetry.platform.iam.domain.user.Email;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Instant;

public class UserRegisteredEvent implements DomainEvent {

    private final Long userId;
    private final Email email;
    private final Instant occurredAt;

    private UserRegisteredEvent(Long userId, Email email, Instant occurredAt) {
        this.userId = userId;
        this.email = email;
        this.occurredAt = occurredAt;
    }

    public static UserRegisteredEvent of(Long userId, Email email) {
        return new UserRegisteredEvent(userId, email, Instant.now());
    }

    public Long getUserId() {
        return userId;
    }

    public Email getEmail() {
        return email;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("email", email)
                .append("occurredAt", occurredAt)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof UserRegisteredEvent)) return false;

        final UserRegisteredEvent that = (UserRegisteredEvent) o;

        return new EqualsBuilder()
                .append(userId, that.userId)
                .append(email, that.email)
                .append(occurredAt, that.occurredAt)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(email)
                .append(occurredAt)
                .toHashCode();
    }
}
