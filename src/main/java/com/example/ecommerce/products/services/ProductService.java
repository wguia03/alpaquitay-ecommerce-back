package com.example.ecommerce.products.services;

import com.example.ecommerce.products.dto.ProductRequest;
import com.example.ecommerce.products.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(Integer id);
    List<ProductResponse> findByName(String name);
    List<ProductResponse> findByCategory(String name);
    ProductResponse save(ProductRequest product);
    ProductResponse update(Integer id, ProductRequest product);
    int updateStock(Integer id, int quantity);
    void deleteById(Integer id);
}
