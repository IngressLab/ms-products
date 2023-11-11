package com.example.ms_products.mapper

import com.example.ms_products.dao.entity.ProductEntity
import com.example.ms_products.model.enums.ProductStatus
import com.example.ms_products.model.request.ProductRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ProductMapperTest extends Specification{

    private EnhancedRandom enhancedRandom= EnhancedRandomBuilder.aNewEnhancedRandom();

    def "Test ProductEntityMapper"(){
        given:
        def request=enhancedRandom.nextObject(ProductRequest)
        when:
        def actual=ProductMapper.productEntityMapper(request)
        then:
        actual.categoryId==request.categoryId
        actual.subcategoryId==request.categoryId
        actual.name==request.name
        actual.description==request.description
        actual.price==request.price
        actual.status==ProductStatus.ACTIVE
        actual.stockCount==request.stockCount

    }
    def "Test buildProductResponse"(){
        given:
        def product =enhancedRandom.nextObject(ProductEntity)
        when:
        def actual=ProductMapper.buildProductResponse(product)
        then:
        actual.id==product.id
        actual.userId==product.userId
        actual.categoryId==product.categoryId
        actual.subcategoryId==product.categoryId
        actual.name==product.name
        actual.description==product.description
        actual.price==product.price
        actual.status==product.status
        actual.stockCount==product.stockCount
        actual.inTopList==product.inTopList
        actual.rating==product.rating
        actual.createdAt==product.createdAt

    }

}
