package com.example.ecommerce.products.repositories;

import com.example.ecommerce.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE p.productCategory.name = :categoryName")
    List<Product> findByCategoryName(String categoryName);
}
