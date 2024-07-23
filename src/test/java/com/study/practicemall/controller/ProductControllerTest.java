package com.study.practicemall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.practicemall.dto.request.ProductRequestDTO;
import com.study.practicemall.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * 2024.07.11 ProductController 에 Unit Test 를 실행해본다.
 * 1.MockMVC를 사용해 특정요청에 의해 알맞는 서비스의 메소드를 호출했는지 & 의도한 형식이 반환되는지 확인한다.
 * 2.productCode가 입력되지 않았을 경우
 * 3.productName가 입력되지 않았을 경우
 * 4.productPrice가 입력되지 않았을 경우
 * 5.productComment가 입력되지 않았을 경우
 * 6.productPrice가 0이 입력되었을 경우
 * 7.잘못된경로 혹은 없는 경로로 호출했을 경우
 *
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


    /*
     * perform() : 요청을 전송하는 역할
     * andExcpect() : 기대하는 결과 리턴값을 검증하고 확인할 수 있음
     * andDo() : 요청/응답 전체 메세지를 확인
     *
     * */
    @Test
    @DisplayName("상품 등록 성공")
    void addProduct() throws Exception {
        //given
        ProductRequestDTO productRequestDTO = ProductRequestDTO.builder().productCode("1234").productName("나이키덩크").productPrice(119000).productComment("이상품은 운동화입니다.").build();
        //when
        doNothing().when(productService).registerProduct(productRequestDTO);
        mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productRequestDTO))).andExpect(status().isCreated());
        //then
        verify(productService, times(1)).registerProduct(refEq(productRequestDTO));

    }

    /**
     * given:특정 값을 부여할때
     * when:특정요청이 들어왔을때
     * then:원하던 응답형식인지 확인할때
     */
    @Test
    @DisplayName("상품코드에 null 이나 공백이 들어가면 등록에 실패한다.")
    void productNotCode() throws Exception {
        //given
        ProductRequestDTO productRequestDTO = ProductRequestDTO.builder().productCode("").productName("나이키덩크").productPrice(119000).productComment("이 상품은 운동화입니다.").build();
        //when
        /*
         * ResultActions MockMvcResultMatchers 클래스에서 실행결과를 확인할 수 있는 인터페이스
         * */
        doNothing().when(productService).registerProduct(productRequestDTO);
        ResultActions resultActions = mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productRequestDTO)));
        //then
        resultActions.andExpect(status().isBadRequest()).andExpect(jsonPath("$.statusCode").value("400")).andExpect(jsonPath("$.message").value("상품코드는 필수 값입니다."));

    }

    @Test
    @DisplayName("상품이름에 null 이나 공백이 들어가면 등록에 실패한다.")
    void productNotName() throws Exception {
        //given
        ProductRequestDTO productRequestDTO = ProductRequestDTO.builder().productCode("1234").productName("").productPrice(119000).productComment("이 상품은 운동화입니다.").build();
        //when
        doNothing().when(productService).registerProduct(productRequestDTO);
        ResultActions resultActions = mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productRequestDTO)));
        //then
        resultActions.andExpect(status().isBadRequest()).andExpect(jsonPath("$.statusCode").value("400")).andExpect(jsonPath("$.message").value("상품이름은 필수 입력 값입니다."));

    }

    @Test
    @DisplayName("상품가격에 공백 이나 0이 들어가면 등록에 실패한다.")
    void productNotPrice() throws Exception {
        //given
        ProductRequestDTO productRequestDTO = ProductRequestDTO.builder().productCode("1234").productName("나이키덩크").productPrice(0).productComment("이 상품은 운동화입니다.").build();
        //when
        doNothing().when(productService).registerProduct(productRequestDTO);
        ResultActions resultActions = mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productRequestDTO)));
        //then
        resultActions.andExpect(status().isBadRequest()).andExpect(jsonPath("$.statusCode").value("400")).andExpect(jsonPath("$.message").value("상품 가격은 0이상의 값만 입력 가능합니다."));

    }

    @Test
    @DisplayName("상품설명에 null 이나 공백이 들어가면 등록에 실패한다.")
    void productNotComment() throws Exception {
        //given
        ProductRequestDTO productRequestDTO = ProductRequestDTO.builder().productCode("1234").productName("나이키덩크").productPrice(119000).productComment("").build();
        //when
        doNothing().when(productService).registerProduct(productRequestDTO);
        ResultActions resultActions = mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productRequestDTO)));
        //then
        resultActions.andExpect(status().isBadRequest()).andExpect(jsonPath("$.statusCode").value("400")).andExpect(jsonPath("$.message").value("상품설명은 필수 입력 값입니다."));

    }


}
