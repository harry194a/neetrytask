package com.neetry.platform.iam.domain.user;

import com.neetry.platform.iam.domain.common.AggregateRoot;
import com.neetry.platform.iam.domain.user.commands.AuthenticateUserCommand;
import com.neetry.platform.iam.domain.user.commands.RegisterUserCommand;
import com.neetry.platform.iam.domain.user.events.UserAuthenticatedEvent;
import com.neetry.platform.iam.domain.user.events.UserRegisteredEvent;
import com.neetry.platform.iam.domain.user.exception.InvalidCredentialsException;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

@Entity
public class User extends AggregateRoot {

    private static final Logger logger = LoggerFactory.getLogger(User.class);
    
    @Embedded
    private Email email;

    @Embedded
    private EncryptedPassword encryptedPassword;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    protected User() {
    }

    public User(RegisterUserCommand command) {
        logger.trace("Handling command - {}", command);
        Assert.notNull(command, "command cannot be null");
        this.email = command.getEmail();
        this.encryptedPassword = EncryptedPassword.fromRawPassword(command.getPassword());
        this.firstname = command.getFirstName();
        this.lastname = command.getLastName();
        this.phone = command.getPhone();
        this.address = command.getAddress();
        this.role = command.getRole();
        addDomainEvent(UserRegisteredEvent.of(getId(), email));
        logger.debug("Successfully handled command - {}", command);
    }

    public User(AuthenticateUserCommand command) {
        logger.trace("Handling command - {}", command);
        Assert.notNull(command, "command cannot be null");
        authenticate(command.getPassword());
        addDomainEvent(UserAuthenticatedEvent.of(getId(), email, firstname, lastname,phone,address));
        logger.debug("Successfully handled command - {}", command);
    }

    public void authenticate(AuthenticateUserCommand command) {
        logger.trace("Handling command - {}", command);
        Assert.notNull(command, "command cannot be null");
        authenticate(command.getPassword());
        addDomainEvent(UserAuthenticatedEvent.of(getId(), email, firstname, lastname,phone,address));
        logger.debug("Successfully handled command - {}", command);
    }
    
    private void authenticate(String password) {
        if (!encryptedPassword.matches(password)) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
    }

    public User(final Email email, 
                final EncryptedPassword encryptedPassword,
                final String firstname,
                final String lastname, 
                final String phone, 
                final String address, 
                final Role role) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(final Email email) {
        this.email = email;
    }

    public EncryptedPassword getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(final EncryptedPassword encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
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

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final User user)) return false;

        return new EqualsBuilder().appendSuper(super.equals(o))
                .append(email, user.email)
                .append(encryptedPassword, user.encryptedPassword)
                .append(firstname, user.firstname)
                .append(lastname, user.lastname)
                .append(phone, user.phone)
                .append(address, user.address)
                .append(role, user.role).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode())
                .append(email)
                .append(encryptedPassword)
                .append(firstname)
                .append(lastname)
                .append(phone)
                .append(address)
                .append(role).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email", email)
                .append("encryptedPassword", encryptedPassword)
                .append("firstname", firstname)
                .append("lastname", lastname)
                .append("phone", phone)
                .append("address", address)
                .append("role", role)
                .append("id", getId())
                .append("createdAt", createdAt)
                .append("lastModifiedAt", lastModifiedAt)
                .toString();
    }
}
