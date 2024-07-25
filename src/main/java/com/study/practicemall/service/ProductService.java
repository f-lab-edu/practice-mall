package com.study.practicemall.service;

import com.study.practicemall.common.exception.CommonCustomException;
import com.study.practicemall.common.exception.ErrorCode;
import com.study.practicemall.dao.ProductDAO;
import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public void registerProduct(ProductRequestDTO productRequestDTO) {
        int existenceProduct = productMapper.countOfProduct(productRequestDTO.getProductCode());
        if (existenceProduct > 0) {
            log.error("duplicate failed");
            throw new CommonCustomException(ErrorCode.PRODUCT_DUPLICATED);
        }
        ProductDAO product = ProductDAO.builder().productCode(productRequestDTO.getProductCode()).productName(productRequestDTO.getProductName()).productPrice(productRequestDTO.getProductPrice()).productComment(productRequestDTO.getProductComment()).build();
        int getResult = productMapper.registerProduct(product);
        if (getResult == 0) {
            log.error("processing failed");
            throw new CommonCustomException(ErrorCode.NOT_REGISTER);
        }
    }
}
