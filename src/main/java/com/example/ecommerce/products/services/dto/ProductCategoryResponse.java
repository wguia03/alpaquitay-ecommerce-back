package com.example.ecommerce.products.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductCategoryResponse {
    Integer id;
    String name;
    Integer parentCategoryId;
}
