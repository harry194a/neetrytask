package com.neetry.platform.iam.application.user.exception;

import com.neetry.platform.iam.domain.common.exception.ErrorCode;
import com.neetry.platform.iam.domain.common.exception.ServiceRuntimeException;

public class EmailAlreadyRegisteredException extends ServiceRuntimeException {
    public EmailAlreadyRegisteredException(final String message) {
        super(message, ErrorCode.USER_EMAIL_EXISTS);
    }
}
