package com.example.ms_products.model.request;

import com.example.ms_products.model.enums.ProductStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import static jakarta.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotNull
    @NotEmpty
    private Long userId;

    @NotNull
    @NotEmpty
    private Long categoryId;

    @NotNull
    @NotEmpty
    private Long subcategoryId;

    @NotNull
    @NotEmpty
    private String name;

    private String text;

    @NotNull
    @NotEmpty
    private Long stockCount;

    @NotNull
    @NotEmpty
    private Double price;



}
