package com.neetry.platform.iam.domain.oauth.jwt;

import com.neetry.platform.iam.domain.oauth.jwt.exception.InvalidJwtTokenException;

public interface JwtService {
    
    String createJwt(CreateJwtCommand command);

    JwtUserInfo parseJwt(String jwt) throws InvalidJwtTokenException;
}
