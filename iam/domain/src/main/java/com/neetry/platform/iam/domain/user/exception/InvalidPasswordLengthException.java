package com.neetry.platform.iam.domain.user.exception;

import com.neetry.platform.iam.domain.common.exception.ErrorCode;
import com.neetry.platform.iam.domain.common.exception.ServiceRuntimeException;

public class InvalidPasswordLengthException extends ServiceRuntimeException {

    public InvalidPasswordLengthException(final String message) {
        super(message, ErrorCode.INVALID_PASSWORD_LENGTH);
    }
}
