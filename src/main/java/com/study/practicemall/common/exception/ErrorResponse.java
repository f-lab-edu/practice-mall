package com.study.practicemall.common.exception;

import lombok.Builder;


@Builder
public class ErrorResponse {
    private int statusCode;
    private String message;
}
