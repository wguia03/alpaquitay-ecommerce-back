package com.example.ecommerce.products.services;

import com.example.ecommerce.products.dto.ProductCategoryRequest;
import com.example.ecommerce.products.models.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory createProductCategory(ProductCategoryRequest productCategory);
    ProductCategory getProductCategoryById(int id);
    ProductCategory getProductCategoryByName(String name);
    List<ProductCategory> getAllProductCategories();
    ProductCategory updateProductCategory(int id, ProductCategory productCategory);
    void deleteProductCategory(int id);
}
