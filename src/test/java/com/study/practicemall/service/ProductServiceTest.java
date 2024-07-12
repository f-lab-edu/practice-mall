package com.study.practicemall.service;

import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/*
 * 2024.07.12 서비스는 트랜잭션과 밀접한 관계를 가지고 있다.
 * 서비스테스트를 통해 데이터베이스 변경을 확인한다.
 * */
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductMapper productMapper;

    @InjectMocks
    ProductService productService;
    ProductRequestDTO productRequestDTO;

    @BeforeEach
    void init() {
        productRequestDTO = ProductRequestDTO.builder().productCode("1234").productName("나이키덩크").productPrice(119000).productComment("이상품은 운동화입니다.").build();
    }

    /*
     * verify : mock 객체에 대해 원하는 메소드가 특정 조건에 의해 발생했는지 확인한다.
     * times : 지정된 수로 호출이 되어야한다.
     * any :모든 테스트가 고정된 값만 사용하지 않음. 여러 상황에서 벌어질 수 있는 결과를 확인하는 것이 테스트의 주 목적이기 때문에 다양한 범위의 값, 혹은 다양한 종류의 값을 사용해야 하는 경우가 생기므로 사용
     * */
    @Test
    void addProduct() {
        productService.registerProduct(productRequestDTO);
        verify(productMapper, times(1)).registerProduct(any());
    }

}
