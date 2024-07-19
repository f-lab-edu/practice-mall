package com.study.practicemall.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ErrorCode {
    PRODUCT_DUPLICATED(700, "중복된 상품입니다."), NOT_REGISTER(900, "등록에 실패했습니다.");
    private final int statusCode;
    private final String message;
}
