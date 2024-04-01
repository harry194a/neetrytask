package com.neetry.platform.iam.domain.oauth.jwt.exception;

import com.neetry.platform.iam.domain.common.exception.ErrorCode;
import com.neetry.platform.iam.domain.common.exception.ServiceRuntimeException;

public class InvalidJwtTokenException extends ServiceRuntimeException {
    public InvalidJwtTokenException(final String message) {
        super(message, ErrorCode.INVALID_JWT_TOKEN);
    }
}
