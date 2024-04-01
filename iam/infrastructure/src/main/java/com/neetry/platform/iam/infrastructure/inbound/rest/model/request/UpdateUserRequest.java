package com.neetry.platform.iam.infrastructure.inbound.rest.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neetry.platform.iam.domain.user.commands.UpdateUserCommand;
import jakarta.validation.constraints.NotEmpty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Harutyun Badeyan
 * Date: 31.03.24
 * Time: 15:55
 */
public class UpdateUserRequest {

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

    @JsonIgnore
    public UpdateUserCommand toCommand() {
        return new UpdateUserCommand(
                id,
                firstname,
                lastname,
                phone,
                address
        );
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
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

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final UpdateUserRequest that)) return false;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstname", firstname)
                .append("lastname", lastname)
                .append("phone", phone)
                .append("address", address)
                .toString();
    }
}
