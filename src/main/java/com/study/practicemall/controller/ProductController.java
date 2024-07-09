package com.study.practicemall.controller;

import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.dto.response.ProductResponseDTO;
import com.study.practicemall.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*

@Vaild을 통해 유효성 검증 메세지를 확인해본다.
 스프링부트가 요청을 받았을 때 요청한 값이 null 이나 blank 일 경우 어노테이션에 지정한 메세지가 응답 바디에 나오는지 테스트한다.
 스프링부트가 요청을 받았을 때 요청한 값이 null 이나 blank 일 경우 어떠한 응답 상태가 나오는지 테스트한다.*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponseDTO> registerProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        productService.registerProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDTO.builder().build());
    }
}
