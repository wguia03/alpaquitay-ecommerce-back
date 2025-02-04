package com.example.ecommerce.services;

import com.example.ecommerce.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();

    ProductResponse findById(Integer id);
}
