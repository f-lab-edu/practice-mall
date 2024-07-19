package com.study.practicemall.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DataAccessCheckException extends RuntimeException {
    private final ErrorCode errorCode;
}
