package com.study.practicemall.service;

import com.study.practicemall.dao.ProductDAO;
import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.dto.response.ProductResponseDTO;
import com.study.practicemall.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    public void saveProductInfo(ProductRequestDTO productRequestDTO){
        ProductDAO product = ProductDAO.builder()
                .productCode(productRequestDTO.getProductCode())
                .productName(productRequestDTO.getProductName())
                .productPrice(productRequestDTO.getProductPrice())
                .productComent(productRequestDTO.getProductComent())
                .build();
        productMapper.saveProductInfo(product);
    }


}
