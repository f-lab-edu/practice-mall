package com.study.practicemall.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * RequiredArgsConstructor : 초기화 되지않은 final 필드에 생성자를 생성해준다.
 */
@Getter
@RequiredArgsConstructor
public class DuplicateCheckException extends RuntimeException {
    private final ErrorCode errorCode;
}
