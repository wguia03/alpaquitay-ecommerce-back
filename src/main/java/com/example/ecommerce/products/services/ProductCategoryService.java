package com.example.ecommerce.products.services;

import com.example.ecommerce.products.services.dto.ProductCategoryRequest;
import com.example.ecommerce.products.models.ProductCategory;
import com.example.ecommerce.products.services.dto.ProductCategoryResponse;

import java.util.List;

public interface ProductCategoryService {
    ProductCategoryResponse createProductCategory(ProductCategoryRequest productCategory);
    ProductCategoryResponse getProductCategoryById(int id);
    ProductCategoryResponse getProductCategoryByName(String name);
    List<ProductCategoryResponse> getAllProductCategories();
    ProductCategoryResponse updateProductCategory(int id, ProductCategoryRequest productCategory);
    void deleteProductCategory(int id);
}
