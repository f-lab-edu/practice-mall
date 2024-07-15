package com.study.practicemall.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductRequestDTO {

    @NotEmpty(message = "상품코드는 필수 값입니다.")
    private String productCode;

    @NotBlank(message = "상품이름은 필수 입력 값입니다.")
    private String productName;

    @NotNull(message = "상품가격은 필수 입력 값입니다.")
    @Positive(message = "상품 가격은 0이상의 값만 입력 가능합니다.")
    private int productPrice;

    @NotBlank(message = "상품이름은 필수 입력 값입니다.")
    private String productComment;

}
