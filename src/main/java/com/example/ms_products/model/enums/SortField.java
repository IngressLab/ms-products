package com.example.ms_products.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortField {
    ID("id"),NAME("name"),PRICE("price"),RATING("rating");

    private final String fieldName;
}
