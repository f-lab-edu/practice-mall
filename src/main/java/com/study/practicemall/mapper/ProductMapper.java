package com.study.practicemall.mapper;

import com.study.practicemall.dao.ProductDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    int registerProduct(ProductDAO product);

    int countOfProduct(String productCode);
}
