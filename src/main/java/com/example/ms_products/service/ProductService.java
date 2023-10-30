package com.example.ms_products.service;

import com.example.ms_products.dao.entity.ProductEntity;
import com.example.ms_products.dao.repository.ProductRepository;
import com.example.ms_products.exception.FileStorageException;
import com.example.ms_products.exception.NotFoundException;
import com.example.ms_products.model.criteria.PageCriteria;
import com.example.ms_products.model.criteria.ProductCriteria;
import com.example.ms_products.model.criteria.SortingCriteria;
import com.example.ms_products.model.enums.ProductStatus;
import com.example.ms_products.model.request.ProductRequest;
import com.example.ms_products.model.request.ProductUpdateRequest;
import com.example.ms_products.model.response.PageableProductResponse;
import com.example.ms_products.model.response.ProductResponse;
import com.example.ms_products.service.specification.ProductSpecification;
import com.example.ms_products.util.SortingUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Base64;

import static com.example.ms_products.exception.ExceptionMessages.PRODUCT_NOT_FOUND;
import static com.example.ms_products.mapper.ProductMapper.*;
import static com.example.ms_products.model.enums.ProductStatus.DELETED;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SortingUtil sortingUtil;

    private ProductEntity fetchProductIfExist(Long id){
        return productRepository.findById(id).orElseThrow(
                ()->new NotFoundException(PRODUCT_NOT_FOUND.getMessage())
        );
    }
    public ProductResponse getProductById(Long id){
        var product=fetchProductIfExist(id);
        return buildProductResponse(product);
    }
    public void  deleteProduct(Long id){

        var product=fetchProductIfExist(id);
        product.setStatus(DELETED);
        productRepository.save(product);
    }
    public PageableProductResponse getProducts(PageCriteria pageCriteria, SortingCriteria sortingCriteria, ProductCriteria productCriteria){

        var orders=sortingUtil.buildSortOrders(sortingCriteria);
        var productsPage=productRepository.findAll(new ProductSpecification(productCriteria),
                PageRequest.of(pageCriteria.getPage(),pageCriteria.getCount(),Sort.by(orders)));
        return buildPageableProductResponse(productsPage);
    }
    public void saveProduct(Long userId,MultipartFile file, ProductRequest request){

        var productEntity= productEntityMapper(request);
        productEntity.setUserId(userId);
       try {
                byte[] imageArr = file.getBytes();
                String imageAsString = Base64.getEncoder().encodeToString(imageArr);
                productEntity.setImageContent(imageAsString);
       } catch (IOException e) {
                throw new FileStorageException("File upload error");
       }
        productRepository.save(productEntity);

    }
    public void updateProduct(Long id, ProductUpdateRequest request) {

        var product = fetchProductIfExist(id);

        updateProductMapper(product, request);

        productRepository.save(product);
    }
    public void updateProductWithStatus(Long id, String status) {

        var product = fetchProductIfExist(id);

        product.setStatus(ProductStatus.valueOf(status));

        productRepository.save(product);
    }

    public ProductResponse getProductByIdAndStatus(Long id){
        var product=productRepository.findByIdAndStatus(id,ProductStatus.ACTIVE).orElseThrow(
                ()->new NotFoundException(PRODUCT_NOT_FOUND.getMessage())
        );
        return buildProductResponse(product);
    }

    public ProductResponse getProductByIdAndStatusAndStockCount(Long id,Long stockCount){
        var product=productRepository.findByIdAndStatus(id,ProductStatus.ACTIVE)
                .filter(prod->prod.getStockCount()>=stockCount)
                .orElseThrow(()->new NotFoundException(PRODUCT_NOT_FOUND.getMessage())
        );
        return buildProductResponse(product);
    }




}
