package com.study.practicemall.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 각 error를 정의해보자
 */

@RestControllerAdvice
public class ErrorHandler {
    /*
     * @Vaild 에 대한 Exception
     * */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        //return new ErrorResponse(400, e.getFieldError().getDefaultMessage());
        return new ErrorResponse(400, e.getFieldError().getDefaultMessage());
    }

    /**
     * 데이터 중복에 대한 Exception
     */
    @ExceptionHandler(CommonCustomException.class)
    public ErrorResponse duplicateCheckException(CommonCustomException e) {
        return new ErrorResponse(e.getErrorCode().getStatusCode(), e.getErrorCode().getMessage());
    }
}
