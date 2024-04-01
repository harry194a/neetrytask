package com.neetry.platform.book.adapter.exeption;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 20:29
 */
public class ServiceException extends RuntimeException {

    private final ErrorCode errorCode;

    public ServiceException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
