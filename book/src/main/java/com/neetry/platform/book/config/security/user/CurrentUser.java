package com.neetry.platform.book.config.security.user;


import com.neetry.platform.iam.domain.oauth.jwt.JwtUserInfo;

public interface CurrentUser {

    JwtUserInfo getJwtUserInfo();
}
