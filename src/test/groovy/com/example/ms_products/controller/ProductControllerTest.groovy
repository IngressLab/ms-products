package com.example.ms_products.controller

import com.example.ms_products.exception.ErrorHandler
import com.example.ms_products.model.enums.ProductStatus
import com.example.ms_products.model.response.ProductResponse
import com.example.ms_products.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDateTime

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals

class ProductControllerTest extends Specification{

    private ProductService productService
    private MockMvc mockMvc

    void setup() {
        productService=Mock()
        def productController=new ProductController(productService)
        mockMvc= MockMvcBuilders.standaloneSetup(productController)
                 .setControllerAdvice(new ErrorHandler())
                 .build()
    }

    def "TestGetProductById"(){
        given:
        def url="/v1/products/1"

        def responseView=new ProductResponse(1,2,3,4,"","test","description",false,5,5.05,
                ProductStatus.ACTIVE,4.5, LocalDateTime.of(2023,10,10,12,1))
        def expectedResponse='''
                              {
                                "id": 1,
                                "userId": 2,
                                "categoryId": 3,
                                "subcategoryId": 4,
                                "image": "",
                                "name": "test",
                                "description": "description",
                                "inTopList": false,
                                "stockCount": 5,
                                "price": 5.05,
                                "status": "ACTIVE",
                                "rating": 4.5,
                                "createdAt": [2023,10,10,12,1]
                              }
                                 '''
        when:
        def result=mockMvc.perform(get(url)).andReturn()
        then:
        1 * productService.getProductById(id) >> responseView
        def response=result.response
        response.status== HttpStatus.OK.value()
        assertEquals(expectedResponse,response.getContentAsString(),false)

    }
}
