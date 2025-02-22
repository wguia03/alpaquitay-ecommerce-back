package com.example.ecommerce.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponse {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock_quantity;
    private String image_url;
    private String category;
    private int seller_id;
}
