package com.example.ms_products.model.response;

import com.example.ms_products.model.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private Long userId;

    private Long categoryId;

    private Long subcategoryId;

    private String image;

    private String name;

    private String description;

    private Boolean inTopList;

    private Long stockCount;

    private BigDecimal price;

    private ProductStatus status;

    private Double rating;

    private LocalDateTime createdAt;

}
