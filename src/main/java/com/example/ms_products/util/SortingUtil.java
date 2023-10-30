package com.example.ms_products.util;

import com.example.ms_products.model.criteria.SortingCriteria;
import com.example.ms_products.model.enums.SortDirection;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Component
public class SortingUtil {

    private final List<Sort.Order> orders = new ArrayList<>();


    private void getOrder(String field, SortDirection direction){

        if(field!=null && direction!=null){
         orders.add(new Sort.Order(Direction.valueOf(direction.name()), field));
        }
    }

    public  List<Sort.Order> buildSortOrders(SortingCriteria sortingCriteria){

        getOrder("createdAt",sortingCriteria.getCreatedAt());
        getOrder("name",sortingCriteria.getName());
        getOrder("rating",sortingCriteria.getRating());
        getOrder("price", sortingCriteria.getPrice());
        orders.add(new Sort.Order(DESC,"inTopList"));

        return orders;
    }

}
