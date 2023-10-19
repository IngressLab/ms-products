package com.example.ms_products.dao.repository;

import com.example.ms_products.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
