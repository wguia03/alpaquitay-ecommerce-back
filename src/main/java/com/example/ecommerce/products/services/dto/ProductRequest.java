package com.example.ecommerce.products.services.dto;

import com.example.ecommerce.products.models.ProductCategory;
import com.example.ecommerce.users.models.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String imageUrl;
    private Integer productCategoryId;
    private Integer sellerId;
}
