package com.example.ms_products.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessages {

    UNEXPECTED_ERROR("Unexpected Error"),
    PRODUCT_NOT_FOUND("Not Found with id");

    private final String message;
}
