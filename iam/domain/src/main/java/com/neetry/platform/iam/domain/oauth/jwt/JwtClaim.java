package com.neetry.platform.iam.domain.oauth.jwt;

public class JwtClaim {

    private JwtClaim() {
    }

    public static final String EMAIL = "email";
    public static final String SUB = "sub";
    public static final String ID = "id";
    public static final String EXPIRATION = "exp";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String AUTHORITIES = "authorities";
}
