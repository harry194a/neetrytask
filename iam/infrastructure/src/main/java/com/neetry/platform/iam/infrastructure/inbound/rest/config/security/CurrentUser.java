package com.neetry.platform.iam.infrastructure.inbound.rest.config.security;


import com.neetry.platform.iam.domain.oauth.jwt.JwtUserInfo;

public interface CurrentUser {

    JwtUserInfo getJwtUserInfo();
}
