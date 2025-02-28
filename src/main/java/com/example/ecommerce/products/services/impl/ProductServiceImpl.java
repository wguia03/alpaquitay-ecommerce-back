package com.example.ecommerce.products.services.impl;

import com.example.ecommerce.products.services.dto.ProductRequest;
import com.example.ecommerce.products.services.dto.ProductResponse;
import com.example.ecommerce.products.models.Product;
import com.example.ecommerce.products.models.ProductCategory;
import com.example.ecommerce.products.repositories.ProductCategoryRepository;
import com.example.ecommerce.products.repositories.ProductRepository;
import com.example.ecommerce.products.services.ProductService;
import com.example.ecommerce.users.models.Seller;
import com.example.ecommerce.users.repositories.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final ProductCategoryRepository productCategoryRepository;

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

    @Override
    public List<ProductResponse> findByName(String name) {
        List<Product> products = productRepository.findAllByNameContainingIgnoreCase(name);
        return fromProductsToProductResponses(products);
    }

    @Override
    public List<ProductResponse> findByCategoryId(Integer id) {
        List<Product> products = productRepository.findByProductCategory_Id(id);
        return fromProductsToProductResponses(products);
    }

    @Override
    public ProductResponse save(ProductRequest productRequest) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productRequest.getProductCategoryId());
        Optional<Seller> seller = sellerRepository.findById(productRequest.getSellerId());
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stockQuantity(productRequest.getStockQuantity())
                .imageUrl(productRequest.getImageUrl())
                .productCategory(productCategory.orElseThrow())
                .seller(seller.orElseThrow())
                .build();
        Product savedProduct = productRepository.save(product);
        return fromProductToProductResponse(savedProduct);
    }

    @Override
    public ProductResponse update(Integer id, ProductRequest productRequest) {
        Optional<Product> product = productRepository.findById(id);
        Product newProduct = mapProductUpdate(product, productRequest);
        productRepository.save(newProduct);
        return fromProductToProductResponse(newProduct);
    }

    private Product mapProductUpdate(Optional<Product> product, ProductRequest productRequest) {
        if(!productRequest.getName().isEmpty()){
            product.get().setName(productRequest.getName());
        }
        if(!productRequest.getDescription().isEmpty()){
            product.get().setDescription(productRequest.getDescription());
        }
        if(productRequest.getPrice() != null){
            product.get().setPrice(productRequest.getPrice());
        }
        if(productRequest.getStockQuantity() != null){
            product.get().setStockQuantity(productRequest.getStockQuantity());
        }
        if(!productRequest.getImageUrl().isEmpty()){
            product.get().setImageUrl(productRequest.getImageUrl());
        }
        if(productRequest.getProductCategoryId() != null){
            Optional<ProductCategory> productCategory = productCategoryRepository.findById(productRequest.getProductCategoryId());
            product.get().setProductCategory(productCategory.get());
        }
        if(productRequest.getSellerId() != null){
            Optional<Seller> seller = sellerRepository.findById(productRequest.getSellerId());
            product.get().setSeller(seller.get());
        }
        return product.get();
    }

    @Override
    public int updateStock(Integer id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);
        return product.getStockQuantity();
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    private ProductResponse fromProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .imageUrl(product.getImageUrl())
                .productCategoryId(product.getProductCategory().getId())
                .sellerId(product.getSeller().getId())
                .build();
    }

    private List<ProductResponse> fromProductsToProductResponses(List<Product> products) {
        return products.stream()
                .map(this::fromProductToProductResponse)
                .toList();
    }
}