package com.example.ms_products.controller;


import com.example.ms_products.model.response.ProductResponse;
import com.example.ms_products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("internal/v1/products")
public class InternalProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductResponse getProductByIdAndStatus(@PathVariable Long id){

        return productService.getProductByIdAndStatus(id);
    }
    @GetMapping("/{id}/available-in-stock")
    public ProductResponse getProductByIdAndStatusAndStockCount(@PathVariable Long id,@RequestParam Long stockCount){

        return productService.getProductByIdAndStatusAndStockCount(id,stockCount);
    }


}
