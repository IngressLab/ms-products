package com.example.ms_products.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {

    @NotNull
    @NotEmpty
    private Long categoryId;

    @NotNull
    @NotEmpty
    private Long subcategoryId;

    @NotNull
    @NotEmpty
    private String name;

    private String description;

    @NotNull
    @NotEmpty
    private Long stockCount;

    @NotNull
    @NotEmpty
    private BigDecimal price;



}
