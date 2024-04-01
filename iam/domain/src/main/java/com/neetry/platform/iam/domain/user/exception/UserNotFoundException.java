package com.neetry.platform.iam.domain.user.exception;

import com.neetry.platform.iam.domain.common.exception.ErrorCode;
import com.neetry.platform.iam.domain.common.exception.ServiceRuntimeException;

public class UserNotFoundException extends ServiceRuntimeException {
    
    public UserNotFoundException(final String message) {
        super(message, ErrorCode.USER_NOT_FOUND);
    }
}
