package com.neetry.platform.iam.domain.user;

public interface PasswordEncoderService {

    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
