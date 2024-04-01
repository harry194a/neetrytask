package com.neetry.platform.iam.infrastructure.inbound.rest.model.response;


import com.neetry.platform.iam.domain.common.exception.ErrorCode;

public class ErrorResponse {
    
    private ErrorCode errorCode;
    private String message;

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorResponse() {
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
