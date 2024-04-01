package com.neetry.platform.iam.domain.user.commands;

import jakarta.validation.constraints.NotEmpty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Harutyun Badeyan
 * Date: 31.03.24
 * Time: 15:35
 */
public class UpdateUserCommand {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String address;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getId() {
        return id;
    }
    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final UpdateUserCommand that)) return false;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getFirstname(), that.getFirstname())
                .append(getLastname(), that.getLastname())
                .append(getPhone(), that.getPhone())
                .append(getAddress(), that.getAddress()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getFirstname())
                .append(getLastname())
                .append(getPhone())
                .append(getAddress()).toHashCode();
    }

    public UpdateUserCommand(
            final Long id,
            final String firstName,
            final String lastName,
            final String phone,
            final String address
    ) {
        this.id = id;
        this.firstname = firstName;
        this.lastname = lastName;
        this.phone = phone;
        this.address = address;
    }
}
