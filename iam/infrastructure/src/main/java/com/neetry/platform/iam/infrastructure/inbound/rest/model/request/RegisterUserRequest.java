package com.neetry.platform.iam.infrastructure.inbound.rest.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neetry.platform.iam.domain.user.Role;
import com.neetry.platform.iam.domain.user.commands.RegisterUserCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RegisterUserRequest {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String address;

    @JsonIgnore
    public RegisterUserCommand toCommand(Role role) {
        return new RegisterUserCommand(
                email,
                password,
                firstName,
                lastName,
                phone,
                address,
                role);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final RegisterUserRequest that)) return false;

        return new EqualsBuilder()
                .append(getEmail(), that.getEmail())
                .append(getPassword(), that.getPassword())
                .append(getFirstName(), that.getFirstName())
                .append(getLastName(), that.getLastName())
                .append(getPhone(), that.getPhone())
                .append(getAddress(), that.getAddress())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getEmail())
                .append(getPassword())
                .append(getFirstName())
                .append(getLastName())
                .append(getPhone())
                .append(getAddress())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email", email)
                .append("password", password)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("phone", phone)
                .append("address", address)
                .toString();
    }
}
