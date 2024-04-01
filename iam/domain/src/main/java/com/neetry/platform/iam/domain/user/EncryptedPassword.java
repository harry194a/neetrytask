package com.neetry.platform.iam.domain.user;

import com.neetry.platform.iam.domain.common.validator.BeanValidator;
import com.neetry.platform.iam.domain.user.exception.InvalidPasswordLengthException;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
public class EncryptedPassword {

    public static final int MIN_LEN = 8;
    public static final int MAX_LEN = 50;
    
    private static final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @NotEmpty
    private String password;

    protected EncryptedPassword() {
    }

    private EncryptedPassword(String password) {
        this.password = password;
        BeanValidator.validate(this);
    }

    public static EncryptedPassword fromRawPassword(String rawPassword) {
        validate(rawPassword);
        return new EncryptedPassword(passwordEncoder.encode(rawPassword));
    }

    public boolean matches(String rawPassword) {
        return passwordEncoder.matches(rawPassword, password);
    }

    private static void validate(String rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("Password should not be null");
        }
        checkMinLen(rawPassword);
        checkMaxLen(rawPassword);
    }

    private static  void checkMinLen(String rawPassword) {
        if (rawPassword.length() < MIN_LEN) {
            throw new InvalidPasswordLengthException(String.format("Password len should be minimum %d", MIN_LEN));
        }
    }

    private static  void checkMaxLen(String rawPassword) {
        if (rawPassword.length() > MAX_LEN) {
            throw new InvalidPasswordLengthException(String.format("Password len should be maximum %d", MAX_LEN));
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("password", password)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof EncryptedPassword)) return false;

        final EncryptedPassword that = (EncryptedPassword) o;

        return new EqualsBuilder()
                .append(password, that.password)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(password)
                .toHashCode();
    }
}
