package com.study.practicemall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * 2024.07.11 ProductController 에 Unit Test 를 실행해본다.
 * MockMVC를 사용해 응답을 json 객체로 받아오는지 확인한다.
 * WebMvcTest : ??
 * */
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    /*
     * 스프링 MVC 동작을 재현할 수 있는 클래스
     * */
    @Autowired
    private MockMvc mockMvc;

    /*
     * java 객체를 JSON으로 직렬화
     * ObjectMapper클래스를 사용하면 writeValueAsString 및 writeValueAsBytes 메서드는 Java 객체에서 JSON을 생성하고 생성된 JSON을 문자열 또는 바이트 배열로 반환한다.
     * */
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;
    private ProductRequestDTO productRequestDTO;

    @BeforeEach
    void init() {
        productRequestDTO = ProductRequestDTO.builder().productCode("1234").productName("나이키덩크").productPrice(119000).productComment("이상품은 운동화입니다.").build();
    }

    /*
     * perform() : 요청을 전송하는 역할
     * andExcpect() : 결과 리턴값을 검증하고 확인할 수 있음
     * andDo() : 요청/응답 전체 메세지를 확인
     *
     * */
    @Test
    @DisplayName("상품등록API테스트")
    void addProduct() throws Exception {
        mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productRequestDTO))).andExpect(status().isCreated()).andDo(print());
    }

}
