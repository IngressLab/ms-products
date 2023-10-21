package com.example.ms_products.controller;


import com.example.ms_products.model.request.ProductRequest;
import com.example.ms_products.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/upload")
    @ResponseStatus(CREATED)
    public String saveProduct(@Validated @RequestParam MultipartFile file){

        return service.saveImage(file);
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public void saveProduct(@RequestPart("file") MultipartFile file,
                                     @RequestPart("data") ProductRequest request){
        service.saveProduct(file, request);

    }






}
