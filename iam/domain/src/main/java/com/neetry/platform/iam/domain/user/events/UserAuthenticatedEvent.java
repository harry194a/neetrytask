package com.neetry.platform.iam.domain.user.events;

import com.neetry.platform.iam.domain.common.DomainEvent;
import com.neetry.platform.iam.domain.user.Email;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Instant;

public class UserAuthenticatedEvent implements DomainEvent {

    private final Long userId;
    private final Email email;
    private final Instant occurredAt;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String address;

    public UserAuthenticatedEvent(final Long userId,
                                  final Email email,
                                  final Instant occurredAt,
                                  final String firstName,
                                  final String lastName,
                                  final String phone,
                                  final String address) {
        this.userId = userId;
        this.email = email;
        this.occurredAt = occurredAt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public static UserAuthenticatedEvent of(Long userId,
                                            Email email,
                                            String firstName,
                                            String lastName,
                                            String phone,
                                            String address) {
        return new UserAuthenticatedEvent(userId, email, Instant.now(), firstName, lastName, phone, address);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final UserAuthenticatedEvent that)) return false;

        return new EqualsBuilder()
                .append(userId, that.userId)
                .append(email, that.email)
                .append(occurredAt, that.occurredAt)
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .append(phone, that.phone)
                .append(address, that.address).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(email)
                .append(occurredAt)
                .append(firstName)
                .append(lastName)
                .append(phone)
                .append(address).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("email", email)
                .append("occurredAt", occurredAt)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("phone", phone)
                .append("address", address)
                .toString();
    }

}
