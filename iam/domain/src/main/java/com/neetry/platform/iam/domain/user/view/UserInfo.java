package com.neetry.platform.iam.domain.user.view;

import com.neetry.platform.iam.domain.user.Role;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserInfo {

    private final Long userId;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String address;
    private final Role role;

    public UserInfo(Long userId, String email, String firstName, String lastName, String phone, String address, Role role) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final UserInfo userInfo)) return false;

        return new EqualsBuilder()
                .append(userId, userInfo.userId)
                .append(email, userInfo.email)
                .append(firstName, userInfo.firstName)
                .append(lastName, userInfo.lastName)
                .append(phone, userInfo.phone)
                .append(address, userInfo.address)
                .append(role, userInfo.role).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(email)
                .append(firstName)
                .append(lastName)
                .append(phone)
                .append(address)
                .append(role).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("phone", phone)
                .append("address", address)
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

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Role getRole() {
        return role;
    }
}
