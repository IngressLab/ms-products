package com.example.ms_products.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableProductResponse {
    private List<ProductResponse> products;
    private boolean hasNextPage;
    private int lastPageNumber;
    private Long totalElements;
}
