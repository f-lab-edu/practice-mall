package com.study.practicemall.product;

import com.study.practicemall.controller.ProductController;
import com.study.practicemall.dao.ProductDAO;
import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.mapper.ProductMapper;
import com.study.practicemall.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;


    @Mock
    private ProductMapper productMapper;






}
