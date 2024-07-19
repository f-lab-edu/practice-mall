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
    public String methodArgumentNotValidException(MethodArgumentNotValidException e) {
        //return new ErrorResponse(400, e.getFieldError().getDefaultMessage());
        return e.getFieldError().getDefaultMessage();
    }

    /**
     * 데이터 중복에 대한 Exception
     */
    @ExceptionHandler(DuplicateCheckException.class)
    public ErrorResponse duplicateCheckException(DuplicateCheckException e) {
        return new ErrorResponse(e.getErrorCode().getStatusCode(), e.getErrorCode().getMessage());
    }

    /**
     * DB 문제에 대한 Exception
     */
    @ExceptionHandler(DataAccessCheckException.class)
    public ErrorResponse dataException(DataAccessCheckException e) {
        return new ErrorResponse(e.getErrorCode().getStatusCode(), e.getErrorCode().getMessage());
    }

}
