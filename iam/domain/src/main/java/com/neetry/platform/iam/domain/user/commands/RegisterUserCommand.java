package com.neetry.platform.iam.domain.user.commands;

import com.neetry.platform.iam.domain.user.Email;
import com.neetry.platform.iam.domain.user.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RegisterUserCommand {

    @NotNull
    private final Email email;

    @NotEmpty
    private final String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String address;
    @NotEmpty
    private Role role;

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final RegisterUserCommand that)) return false;

        return new EqualsBuilder()
                .append(getEmail(), that.getEmail())
                .append(getPassword(), that.getPassword())
                .append(getFirstName(), that.getFirstName())
                .append(getLastName(), that.getLastName())
                .append(getPhone(), that.getPhone())
                .append(getAddress(), that.getAddress())
                .append(getRole(), that.getRole()).isEquals();
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
                .append(getRole()).toHashCode();
    }

    public RegisterUserCommand(
            final String email,
            final String password,
            final String firstName,
            final String lastName,
            final String phone,
            final String address,
            final Role role) {
        this.email = Email.from(email);
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }
}
