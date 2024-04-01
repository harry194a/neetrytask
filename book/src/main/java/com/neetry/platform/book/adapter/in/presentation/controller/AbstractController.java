package com.neetry.platform.book.adapter.in.presentation.controller;

import com.neetry.platform.book.adapter.exeption.ErrorCode;
import com.neetry.platform.book.adapter.exeption.ServiceException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 22:09
 */
public abstract class AbstractController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    protected final ErrorResponse returnBadRequestHttpStatus(final IllegalArgumentException e) {
        return new ErrorResponse(ErrorCode.ILLEGAL_ARGUMENT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    protected final <T extends ServiceException> ErrorResponse returnBadRequestHttpStatus(final T e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage());
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    protected final <T extends ConstraintViolationException> ErrorResponse returnBadRequestHttpStatus(final T e) {
        return new ErrorResponse(ErrorCode.VALIDATION_ERROR, e.getMessage());
    }
}