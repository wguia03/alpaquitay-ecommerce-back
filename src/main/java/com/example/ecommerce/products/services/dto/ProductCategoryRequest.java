package com.example.ecommerce.products.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductCategoryRequest {
    String name;
    Integer parentCategoryId;
}
