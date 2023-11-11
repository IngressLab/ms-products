package com.example.ms_products.queue;

import com.example.ms_products.model.queue.RatingDto;
import com.example.ms_products.service.ProductQueueService;
import com.example.ms_products.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductQueueListener {
    private final ObjectMapper objectMapper;
    private final ProductQueueService service;

    @RabbitListener(queues = "${rabbitmq.queue.product}")
    public void consume(String message){
        try {
            var dto=objectMapper.readValue(message, RatingDto.class );
            log.info("ListenerLog.setProductRating.start id:{}",dto.getProductId());
            service.setRating(dto);
            log.info("ListenerLog.setProductRating.end id:{}",dto.getProductId());
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
