package com.example.ms_products.model.response;

import com.example.ms_products.model.enums.ProductStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long userId;

    private Long categoryId;

    private Long subcategoryId;

    private String image;

    private String name;

    private String text;

    private Long stockCount;

    private Double price;

    private ProductStatus status;

    private Double avgStars;

    private LocalDateTime createdAt;

}
