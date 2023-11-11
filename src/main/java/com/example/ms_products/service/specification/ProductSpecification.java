package com.example.ms_products.service.specification;

import com.example.ms_products.dao.entity.ProductEntity;
import com.example.ms_products.model.criteria.ProductCriteria;
import com.example.ms_products.model.enums.ProductStatus;
import com.example.ms_products.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.ms_products.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class ProductSpecification implements Specification<ProductEntity> {

    private ProductCriteria productCriteria;

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicates= PredicateUtil.builder()
                        .addNullSafety(productCriteria.getProductName(),
                         name->criteriaBuilder.like(root.get("name"),applyLikePattern(name)))
                        .addNullSafety(productCriteria.getPriceFrom(),
                         priceFrom->criteriaBuilder.greaterThanOrEqualTo(root.get("price"),priceFrom))
                        .addNullSafety(productCriteria.getPriceTo(),
                         priceTo->criteriaBuilder.lessThanOrEqualTo(root.get("price"),priceTo))
                        .addNullSafety(productCriteria.getRating(),
                          rating->criteriaBuilder.equal(root.get("rating"),rating))
                        .add(ProductStatus.ACTIVE, status->criteriaBuilder.equal(root.get("status"),status))
                        . build();
        return criteriaBuilder.and(predicates);
    }
}
