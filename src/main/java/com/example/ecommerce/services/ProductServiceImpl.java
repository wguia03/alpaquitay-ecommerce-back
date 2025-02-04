package com.example.ecommerce.services;

import com.example.ecommerce.dto.ProductResponse;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> findAll() {
        List<Product> product = productRepository.findAll();
        return fromProductsToProductResponses(product);
    }

    @Override
    public ProductResponse findById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return fromProductToProductResponse(product);
    }

    private ProductResponse fromProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock_quantity(product.getStock_quantity())
                .image_url(product.getImage_url())
                .category(product.getProductCategory().getName())
                .seller_id(product.getSeller().getId())
                .build();
    }

    private List<ProductResponse> fromProductsToProductResponses(List<Product> products) {
        return products.stream()
                .map(this::fromProductToProductResponse)
                .toList();
    }
}