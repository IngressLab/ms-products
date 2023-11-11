package com.example.ms_products.service

import com.example.ms_products.dao.entity.ProductEntity
import com.example.ms_products.dao.repository.ProductRepository
import com.example.ms_products.exception.ExceptionMessages
import com.example.ms_products.exception.NotFoundException
import com.example.ms_products.model.enums.ProductStatus
import com.example.ms_products.model.request.ProductUpdateRequest
import com.example.ms_products.util.SortingUtil
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification


class ProductServiceTest extends Specification{

    private EnhancedRandom random= EnhancedRandomBuilder.aNewEnhancedRandom()
    private ProductRepository productRepository
    private ProductService productService
    private SortingUtil sortingUtil

    void setup(){
        productRepository=Mock()
        productService=new ProductService(productRepository,sortingUtil)
    }

    def "Test getProductById success"(){
        given:
        def id=random.nextLong();
        def entity=random.nextObject(ProductEntity)
        when:
        def actual=productService.getProductById(id)
        then:
        1 * productRepository.findById(id) >> Optional.of(entity)
                actual.userId==entity.userId
                actual.categoryId==entity.categoryId
                actual.subcategoryId==entity.subcategoryId
                actual.name==entity.name
                actual.description==entity.description
                actual.status==entity.status
                actual.inTopList==entity.inTopList
                actual.price==entity.price
                actual.stockCount==entity.stockCount
                actual.rating==entity.rating
                actual.createdAt==entity.createdAt
    }
    def "Test getProductById data not found"(){
        given:
        def id=random.nextLong();
        when:
        productService.getProductById(id)
        then:
        1 * productRepository.findById(id) >> Optional.empty()
        NotFoundException ex=thrown()
        ex.message== ExceptionMessages.PRODUCT_NOT_FOUND.getMessage()

    }
    def "Test updateProduct success"(){

        given:
        def id=random.nextLong()
        def request=random.nextObject(ProductUpdateRequest)
        def entity=random.nextObject(ProductEntity)
        when:
        productService.updateProduct(id,request)
        then:
        1 * productRepository.findById(id) >> Optional.of(entity)
        1 * productRepository.save(entity)
        request.name==entity.name
        request.categoryId==entity.categoryId
        request.subcategoryId==entity.subcategoryId
        request.description==entity.description
        request.stockCount==entity.stockCount
        request.price==entity.price

    }
    def "Test updateProduct error when product not found"(){
        given:
        def id=random.nextLong()
        def request=random.nextObject(ProductUpdateRequest)
        when:
        productService.updateProduct(id,request)
        then:
        1 * productRepository.findById(id) >> Optional.empty()
        0 * productRepository.save()

        NotFoundException ex=thrown()
        ex.message== ExceptionMessages.PRODUCT_NOT_FOUND.getMessage()
    }
    def "TestDeleteProduct success"(){
        given:
        def id=random.nextLong();
        def product=random.nextObject(ProductEntity)

        when:
        productService.deleteProduct(id);

        then:
        1 * productRepository.findById(id) >>Optional.of(product)

        1 * productRepository.save(product);

        product.status== ProductStatus.DELETED

    }
    def "TestDeleteProduct error"(){
        given:
        def id=random.nextLong()

        when:
        productService.deleteProduct(id);

        then:
        1 * productRepository.findById(id) >>Optional.empty()

        0 * productRepository.save()

        NotFoundException ex=thrown()
        ex.message== ExceptionMessages.PRODUCT_NOT_FOUND.getMessage()

    }
    def "TestUpdateProductWithStatus success"(){

        given:
        def id=random.nextLong()
        def status="ACTIVE"
        def product=random.nextObject(ProductEntity,"status")
        when:
        productService.updateProductWithStatus(id,status)
        then:
        1 * productRepository.findById(id) >> Optional.of(product)
        1 * productRepository.save(product)

        product.status==ProductStatus.valueOf(status)


    }
    def "TestUpdateProductWithParam not found product"(){

        given:
        def id=random.nextLong()
        def status="ACTIVE"

        when:
        productService.updateProductWithStatus(id,status)
        then:
        1 * productRepository.findById(id) >>Optional.empty()

        0 * productRepository.save()

        NotFoundException ex=thrown()
        ex.message== ExceptionMessages.PRODUCT_NOT_FOUND.getMessage()

    }


}
