package com.study.practicemall.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
/*빌더패턴은 객체의 생성과정을 단순화하고 가독성과 유연성을 향상시키는 디자인패턴
* 해당 어노테이션을 사용하면 클래스에 대한 빌더클래스를 자동으로 생성한다.*/
public class ProductDAO {
    private Long productId;
    private String productCode;
    private String productName;
    private String productPrice;
    private String productComent;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;




    /*public ProductDAO(String product_code, String product_name, String productPrice, String productComent) {

        this.product_code = product_code;
        this.product_name = product_name;
        this.product_price = productPrice;
        this.product_coment = productComent;
    }*/
}
