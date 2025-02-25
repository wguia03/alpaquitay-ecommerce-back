package com.example.ecommerce.products.controllers;

import com.example.ecommerce.products.services.ProductCategoryService;
import com.example.ecommerce.products.services.dto.ProductCategoryRequest;
import com.example.ecommerce.products.services.dto.ProductCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<List<ProductCategoryResponse>> getAllProductCategories() {
        return ResponseEntity.ok(productCategoryService.getAllProductCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryResponse> getProductCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(productCategoryService.getProductCategoryById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductCategoryResponse> getProductCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(productCategoryService.getProductCategoryByName(name));
    }

    @PostMapping("/save")
    public ResponseEntity<ProductCategoryResponse> saveProductCategory(@RequestBody ProductCategoryRequest productCategory) {
        return ResponseEntity.ok(productCategoryService.createProductCategory(productCategory));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductCategoryResponse> updateProductCategory(@PathVariable Integer id, @RequestBody ProductCategoryRequest productCategory) {
        return ResponseEntity.ok(productCategoryService.updateProductCategory(id, productCategory));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Integer id) {
        productCategoryService.deleteProductCategory(id);
        return ResponseEntity.noContent().build();
    }
}
