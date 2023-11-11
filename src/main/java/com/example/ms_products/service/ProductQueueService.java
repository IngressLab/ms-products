package com.example.ms_products.service;

import com.example.ms_products.dao.entity.ProductEntity;
import com.example.ms_products.dao.repository.ProductRepository;
import com.example.ms_products.exception.NotFoundException;
import com.example.ms_products.model.queue.RatingDto;
import com.example.ms_products.model.queue.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.ms_products.exception.ExceptionMessages.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductQueueService {

    private final ProductRepository productRepository;

    private ProductEntity fetchProductIfExist(Long id){
        return productRepository.findById(id).orElseThrow(
                ()->new NotFoundException(PRODUCT_NOT_FOUND.getMessage())
        );
    }

    public void setRating(RatingDto dto){

        var product=fetchProductIfExist(dto.getProductId());

        product.setRating(dto.getRating());

        productRepository.save(product);

    }

    public void setInTopList(SubscriptionDto dto){

        var product=fetchProductIfExist(dto.getProductId());

        product.setInTopList(dto.getInTopList());

        productRepository.save(product);

    }

}
