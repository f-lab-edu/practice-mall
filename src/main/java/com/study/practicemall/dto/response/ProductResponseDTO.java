package com.study.practicemall.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponseDTO {

    private String message;
    private long productId;
    private String productCode;
    private String productName;
    private int productPrice;
    private String productComment;
}
