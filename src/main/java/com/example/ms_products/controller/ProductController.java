package com.example.ms_products.controller;
import com.example.ms_products.model.criteria.PageCriteria;
import com.example.ms_products.model.criteria.ProductCriteria;
import com.example.ms_products.model.criteria.SortingCriteria;
import com.example.ms_products.model.request.ProductRequest;
import com.example.ms_products.model.request.ProductUpdateRequest;
import com.example.ms_products.model.response.PageableProductResponse;

import com.example.ms_products.model.response.ProductResponse;
import com.example.ms_products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id){
        return service.getProductById(id);
    }
    @GetMapping
    public PageableProductResponse getProducts(PageCriteria pageCriteria, SortingCriteria sortingCriteria, ProductCriteria productCriteria){
        return service.getProducts(pageCriteria,sortingCriteria,productCriteria);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveProduct(@RequestHeader("User-Id") Long userId,@RequestPart("file") MultipartFile file,
                                     @RequestPart("data") ProductRequest request){
        service.saveProduct(userId,file, request);

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateProduct(@PathVariable Long id,@RequestBody ProductUpdateRequest request){
        service.updateProduct(id,request);
    }
    @PatchMapping ("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateProductWithStatus(@PathVariable Long id,@RequestParam String status){
        service.updateProductWithStatus(id,status);
    }





}
