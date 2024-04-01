package com.neetry.platform.iam.infrastructure.inbound.rest.controller;


import com.neetry.platform.iam.domain.common.exception.ErrorCode;
import com.neetry.platform.iam.domain.common.exception.ServiceRuntimeException;
import com.neetry.platform.iam.infrastructure.inbound.rest.model.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Component
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    @ResponseBody
    public ErrorResponse returnBadRequestHttpStatus(final IllegalArgumentException e) {
        return new ErrorResponse(ErrorCode.ILLEGAL_STATE, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ServiceRuntimeException.class)
    @ResponseBody
    public <T extends ServiceRuntimeException> ErrorResponse returnBadRequestHttpStatus(final T e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse returnBadRequestHttpStatus(HttpServletRequest req, Exception e) {
        return new ErrorResponse(ErrorCode.SERVER_ERROR, e.getMessage());
    }
}
