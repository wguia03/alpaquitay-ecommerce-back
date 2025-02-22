package com.example.ecommerce.products.repositories;

import com.example.ecommerce.products.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    ProductCategory findByName(String name);
}
