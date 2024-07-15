package com.study.practicemall.service;

import com.study.practicemall.dao.ProductDAO;
import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public void registerProduct(ProductRequestDTO productRequestDTO) {
        int existenceProduct = productMapper.searchProduct(productRequestDTO.getProductCode());
        if (existenceProduct > 0) {
            throw new DuplicateKeyException("이미 등록된 상품입니다.");
        }
        ProductDAO product = ProductDAO.builder().productCode(productRequestDTO.getProductCode()).productName(productRequestDTO.getProductName()).productPrice(productRequestDTO.getProductPrice()).productComment(productRequestDTO.getProductComment()).build();
        System.out.print(product.getProductCode());
        int getResult = productMapper.registerProduct(product);
        System.out.println(getResult);
        if (getResult == 0) {
            throw new RuntimeException("상품등록에 실패했습니다.");
        }
    }
}
