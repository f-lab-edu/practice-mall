package com.study.practicemall.service;

import com.study.practicemall.dao.ProductDAO;
import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
 * 2024.07.12 서비스는 트랜잭션과 밀접한 관계를 가지고 있다.
 * 서비스테스트를 통해 데이터베이스 변경을 확인한다.
 * 1. 상품등록에 성공한다.
 * 2. 상품등록시 이미 등록된 상품인 경우
 * 3. 상품등록에 실패했을 경우 (Mapper 에서 에러가 터지거나, 다른 이유로)
 * */
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductMapper productMapper;

    @InjectMocks
    ProductService productService;
    ProductRequestDTO productRequestDTO;
    ProductDAO productDAO;

    @BeforeEach
    void init() {
        productRequestDTO = ProductRequestDTO.builder().productCode("123456789").productName("나이키덩크").productPrice(119000).productComment("이상품은 운동화입니다.").build();
    }

    public ProductDAO productDAO(ProductRequestDTO productRequestDTO) {
        productDAO = ProductDAO.builder().productCode(productRequestDTO.getProductCode()).productName(productRequestDTO.getProductName()).productPrice(productRequestDTO.getProductPrice()).productComment(productRequestDTO.getProductComment()).build();
        return productDAO;
    }

    /*
     * verify : mock 객체에 대해 원하는 메소드가 특정 조건에 의해 발생했는지 확인한다.
     * times : 지정된 수로 호출이 되어야한다.
     * any :모든 테스트가 고정된 값만 사용하지 않음. 여러 상황에서 벌어질 수 있는 결과를 확인하는 것이 테스트의 주 목적이기 때문에 다양한 범위의 값, 혹은 다양한 종류의 값을 사용해야 하는 경우가 생기므로 사용
     * */
    @Test
    @DisplayName("상품등록 : 성공")
    void addProduct() {
        productService.registerProduct(productRequestDTO);
        verify(productMapper, times(1)).registerProduct(any());
    }

    /**
     * BDDMockito라이브러리 사용하기
     * 가짜로 결과를 만들어 주는 것을 Stubbing(스터빙)
     * when () -> 가짜로 수행할 객체를 넣어준다.
     * assertThrows (기대하는 결과, 실행할 코드)
     */
    @Test
    @DisplayName("상품등록 : 이미 상품이 등록되어있을 경우")
    void duplicationProduct() {
        when(productMapper.searchProduct(productRequestDTO.getProductCode())).thenThrow(new DuplicateKeyException("이미 등록된 상품입니다."));
        assertThrows(DuplicateKeyException.class, () -> productMapper.searchProduct(productRequestDTO.getProductCode()));
    }

    /**
     * 아래의 두 메소드를 통해 예외를 던진다.
     * thenTrow : 값을 반환하는 메서드에 사용
     * doTrows : void 메서드에 사용
     */
    @Test
    @DisplayName("상품등록 : 상품등록에 실패했을 경우")
    void addProductFail() {
        when(productMapper.registerProduct(productDAO)).thenThrow(new RuntimeException("상품등록에 실패했습니다."));
        assertThrows(RuntimeException.class, () -> productMapper.registerProduct(productDAO));
    }

}
