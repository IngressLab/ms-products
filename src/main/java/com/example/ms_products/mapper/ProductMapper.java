package com.example.ms_products.mapper;

import com.example.ms_products.dao.entity.ProductEntity;
import com.example.ms_products.model.enums.ProductStatus;
import com.example.ms_products.model.request.ProductRequest;
import com.example.ms_products.model.request.ProductUpdateRequest;
import com.example.ms_products.model.response.PageableProductResponse;
import com.example.ms_products.model.response.ProductResponse;
import org.springframework.data.domain.Page;

public class ProductMapper {

    public static ProductEntity productEntityMapper(ProductRequest request){
        return  ProductEntity.builder()
                .categoryId(request.getCategoryId())
                .subcategoryId(request.getSubcategoryId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .status(ProductStatus.ACTIVE)
                .stockCount(request.getStockCount())
                .build();

    }
    public static ProductResponse buildProductResponse(ProductEntity entity){

       return ProductResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .categoryId(entity.getCategoryId())
                .subcategoryId(entity.getSubcategoryId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .inTopList(entity.getInTopList())
                .price(entity.getPrice())
                .stockCount(entity.getStockCount())
                .rating(entity.getRating())
                .createdAt(entity.getCreatedAt())
                .build();
    }
    public static void updateProductMapper(ProductEntity entity, ProductUpdateRequest request){

                  entity.setName(request.getName());
                  entity.setCategoryId(request.getCategoryId());
                  entity.setSubcategoryId(request.getSubcategoryId());
                  entity.setDescription(request.getDescription());
                  entity.setStockCount(request.getStockCount());
                  entity.setPrice(request.getPrice());

    }
    public static PageableProductResponse buildPageableProductResponse(Page<ProductEntity> productPage){

        return PageableProductResponse.builder()

                .products(productPage.getContent().stream().map(ProductMapper::buildProductResponse).toList())
                .hasNextPage(productPage.hasNext())
                .lastPageNumber(productPage.getTotalPages())
                .totalElements(productPage.getTotalElements())
                .build();

    }
}
