package com.study.practicemall.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDAO {
    private long productId;
    private String productCode;
    private String productName;
    private int productPrice;
    private String productComment;
}

