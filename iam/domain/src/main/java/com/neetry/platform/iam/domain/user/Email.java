package com.neetry.platform.iam.domain.user;

import com.neetry.platform.iam.domain.common.validator.BeanValidator;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.Assert;

@Embeddable
public class Email {

    @NotEmpty
    @jakarta.validation.constraints.Email
    @Size(max = 255)
    private String emailAddress;

    protected Email() {
    }
    
    private Email(String emailAddress) {
        Assert.hasText(emailAddress, "emailAddress cannot be empty");
        this.emailAddress = format(emailAddress);
        BeanValidator.validate(this);
    }

    public static Email from(String emailAddress) {
        return new Email(emailAddress);
    }

    @Override
    public String toString() {
        return emailAddress;
    }

    private String format(final String email) {
        return email.toLowerCase();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final Email email)) return false;

        return new EqualsBuilder()
                .append(emailAddress, email.emailAddress)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(emailAddress)
                .toHashCode();
    }
}
