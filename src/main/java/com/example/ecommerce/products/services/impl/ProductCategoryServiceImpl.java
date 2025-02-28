package com.example.ecommerce.products.services.impl;

import com.example.ecommerce.products.models.ProductCategory;
import com.example.ecommerce.products.repositories.ProductCategoryRepository;
import com.example.ecommerce.products.services.ProductCategoryService;
import com.example.ecommerce.products.services.dto.ProductCategoryRequest;
import com.example.ecommerce.products.services.dto.ProductCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategoryResponse createProductCategory(ProductCategoryRequest request) {
        ProductCategory productCategory;
        if(request.getParentCategoryId() != null) {
            Optional<ProductCategory> parentCategory = productCategoryRepository.findById(request.getParentCategoryId());
            productCategory = ProductCategory.builder()
                    .name(request.getName())
                    .parentCategory(parentCategory.get())
                    .build();
        } else {
            productCategory = ProductCategory.builder()
                    .name(request.getName())
                    .build();
        }
        return fromProductCategoryToProductCategoryResponse(productCategoryRepository.save(productCategory));
    }

    private ProductCategoryResponse fromProductCategoryToProductCategoryResponse(ProductCategory category) {
        if(category.getParentCategory() == null) {
            return ProductCategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
        } else {
            return ProductCategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .parentCategoryId(category.getParentCategory().getId())
                    .build();
        }
    }

    @Override
    public ProductCategoryResponse getProductCategoryById(int id) {
        return fromProductCategoryToProductCategoryResponse(productCategoryRepository.findById(id).get());
    }

    @Override
    public List<ProductCategoryResponse> getAllProductCategories() {
        return fromProductCategoriesToProductCategoryResponses(productCategoryRepository.findAll());
    }

    private List<ProductCategoryResponse> fromProductCategoriesToProductCategoryResponses(List<ProductCategory> productCategoryList) {
        return productCategoryList.stream()
                .map(this::fromProductCategoryToProductCategoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryResponse updateProductCategory(int id, ProductCategoryRequest productCategory) {
        return fromProductCategoryToProductCategoryResponse(mapProductCategoryUpdate(productCategoryRepository.findById(id), productCategory));
    }

    private ProductCategory mapProductCategoryUpdate(Optional<ProductCategory> productCategory, ProductCategoryRequest request) {
        if(!request.getName().isEmpty()){
            productCategory.get().setName(request.getName());
        }
        if(request.getParentCategoryId() != null){
            Optional<ProductCategory> parentCategory = productCategoryRepository.findById(request.getParentCategoryId());
            productCategory.get().setParentCategory(parentCategory.get());
        }
        return productCategory.get();
    }

    @Override
    public void deleteProductCategory(int id) {
        productCategoryRepository.deleteById(id);
    }
}
