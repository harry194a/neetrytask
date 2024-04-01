package com.neetry.platform.book.adapter.in.presentation.controller;

import com.neetry.platform.book.adapter.exeption.ErrorCode;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 22:19
 */
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
