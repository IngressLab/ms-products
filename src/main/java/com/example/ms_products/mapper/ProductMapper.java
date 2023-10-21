package com.example.ms_products.mapper;

import com.example.ms_products.dao.entity.Product;
import com.example.ms_products.model.enums.ProductStatus;
import com.example.ms_products.model.request.ProductRequest;

public class ProductMapper {

    public static Product productEntityMapper(ProductRequest request){
        return  Product.builder()
                .userId(request.getUserId())
                .categoryId(request.getCategoryId())
                .subcategoryId(request.getSubcategoryId())
                .name(request.getName())
                .text(request.getText())
                .price(request.getPrice())
                .isTop(false)
                .avgStars(0.00)
                .status(ProductStatus.ACTIVE)
                .stockCount(request.getStockCount())
                .build();


    }
}
