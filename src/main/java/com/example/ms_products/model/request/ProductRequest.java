package com.example.ms_products.model.request;

import com.example.ms_products.model.enums.ProductStatus;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long user_id;

    private Long category_id;

    private Long subcategory_id;

    private String image;

    private String name;

    private String text;

    private  Boolean is_top;

    private Long stockCount;

    private Double price;

    private Double money;

    private Double avg_stars;

    private ProductStatus status;
}
