package com.neetry.platform.iam.domain.common.exception;

public class ServiceRuntimeException extends RuntimeException {
    
    private final ErrorCode errorCode;

    public ServiceRuntimeException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
