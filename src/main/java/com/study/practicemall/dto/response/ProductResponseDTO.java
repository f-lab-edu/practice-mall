package com.study.practicemall.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

public class ProductResponseDTO {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReponseDTO{
        private Long productId;
        private String productCode;
        private String productName;
        private String productPrice;
        private String productComent;
    }
}
