package com.example.ecommerce.products.controllers;


import com.example.ecommerce.products.services.ProductService;
import com.example.ecommerce.products.services.dto.ProductRequest;
import com.example.ecommerce.products.services.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductResponse>> getProductByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponse>> getProductByCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findByCategoryId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest product) {
        return ResponseEntity.created(null).body(productService.save(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id, @RequestBody ProductRequest product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    @PutMapping("/updateStock/{id}/{quantity}")
    public ResponseEntity<Integer> updateStock(@PathVariable Integer id, @PathVariable int quantity) {
        return ResponseEntity.ok(productService.updateStock(id, quantity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
