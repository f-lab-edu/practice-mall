package com.study.practicemall.controller;

import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Object> registerProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        productService.registerProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
