package com.example.ms_products.dao.repository;

import com.example.ms_products.dao.entity.ProductEntity;
import com.example.ms_products.model.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long>, JpaSpecificationExecutor<ProductEntity> {


    Optional<ProductEntity> findByIdAndStatus(Long id, ProductStatus status);

}
