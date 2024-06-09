package com.study.practicemall.controller;

import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/productsave")
    public ResponseEntity<Object> saveProductInfo(@RequestBody @Valid ProductRequestDTO productRequestDTO){
        productService.saveProductInfo(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
