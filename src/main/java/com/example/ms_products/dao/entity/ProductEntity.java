package com.example.ms_products.dao.entity;


import com.example.ms_products.model.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long userId;

    private Long categoryId;

    private Long subcategoryId;

    private String imageContent;

    private String name;

    private String description;

    private  Boolean inTopList;

    private Long stockCount;

    private BigDecimal price;

    private Double rating;

    @Enumerated(STRING)
    private ProductStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity productEntity = (ProductEntity) o;
        return Objects.equals(getId(), productEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
