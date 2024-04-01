package com.neetry.platform.iam.infrastructure.inbound.rest.model.response;


import com.neetry.platform.iam.domain.user.Role;
import com.neetry.platform.iam.domain.user.view.UserInfo;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserInfoResponse {

    private Long id;
    private String email;
    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    private Role role;


    public UserInfoResponse(final Long id,
                            final String email,
                            final String firstName,
                            final String lastName,
                            final String phone,
                            final String address, 
                            final Role role) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
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

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public static UserInfoResponse from(UserInfo userInfo) {
        return new UserInfoResponse(
                userInfo.getUserId(), 
                userInfo.getEmail(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhone(),
                userInfo.getAddress(), 
                userInfo.getRole());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final UserInfoResponse that)) return false;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getEmail(), that.getEmail())
                .append(getFirstName(), that.firstName)
                .append(getLastName(), that.lastName)
                .append(getPhone(), that.phone)
                .append(getAddress(), that.address)
                .append(getRole(), that.role).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getEmail())
                .append(getFirstName())
                .append(getLastName())
                .append(getPhone())
                .append(getAddress())
                .append(getRole()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("phone", phone)
                .append("address", address)
                .append("role", role)
                .toString();
    }
}
