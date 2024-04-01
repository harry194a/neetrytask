package com.neetry.platform.iam.domain.user.exception;

import com.neetry.platform.iam.domain.common.exception.ErrorCode;
import com.neetry.platform.iam.domain.common.exception.ServiceRuntimeException;

public class InvalidCredentialsException extends ServiceRuntimeException {
    
    public InvalidCredentialsException(final String message) {
        super(message, ErrorCode.INVALID_CREDENTIALS);
    }
}
