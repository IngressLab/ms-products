package com.example.ms_products.model.criteria;

import com.example.ms_products.model.enums.SortDirection;
import com.example.ms_products.model.enums.SortField;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortingCriteria {

    private SortDirection createdAt;


    private SortDirection name;


    private SortDirection rating;


    private SortDirection price;

}
