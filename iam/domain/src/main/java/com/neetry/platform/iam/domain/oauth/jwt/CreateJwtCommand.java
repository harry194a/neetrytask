package com.neetry.platform.iam.domain.oauth.jwt;

import com.neetry.platform.iam.domain.common.validator.BeanValidator;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class CreateJwtCommand {

    @NotNull
    private final JwtUserInfo jwtUserInfo;

    @NotNull
    private final Instant issuedAt;

    @NotNull
    private final Instant expiresAt;

    public CreateJwtCommand(JwtUserInfo jwtUserInfo, Instant issuedAt, Instant expiresAt) {
        this.jwtUserInfo = jwtUserInfo;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        BeanValidator.validate(this);
    }

    public JwtUserInfo getJwtUserInfo() {
        return jwtUserInfo;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }
}
