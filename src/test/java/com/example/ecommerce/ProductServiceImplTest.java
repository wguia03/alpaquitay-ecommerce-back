package com.example.ecommerce;

import com.example.ecommerce.products.services.dto.ProductRequest;
import com.example.ecommerce.products.services.dto.ProductResponse;
import com.example.ecommerce.products.models.Product;
import com.example.ecommerce.products.models.ProductCategory;
import com.example.ecommerce.products.repositories.ProductCategoryRepository;
import com.example.ecommerce.products.repositories.ProductRepository;
import com.example.ecommerce.products.services.impl.ProductServiceImpl;
import com.example.ecommerce.users.models.Seller;
import com.example.ecommerce.users.repositories.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll () {

        ProductCategory productCategory = ProductCategory.builder()
                .id(1)
                .name("Example Category")
                .build();

        Seller seller = Seller.builder()
                .id(1)
                .email("seller@example.com")
                .build();

        Product product = Product.builder()
                .id(1)
                .name("Example Product")
                .description("This is an example product")
                .price(99.99)
                .stockQuantity(100)
                .imageUrl("http://example.com/image.jpg")
                .productCategory(productCategory)
                .seller(seller)
                .build();

        List<Product> products = List.of(product);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductResponse> responses = productService.findAll();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById () {
        ProductCategory productCategory = ProductCategory.builder()
                .id(1)
                .name("Example Category")
                .build();

        Seller seller = Seller.builder()
                .id(1)
                .email("seller@example.com")
                .build();

        Product product = Product.builder()
                .id(1)
                .name("Example Product")
                .description("This is an example product")
                .price(99.99)
                .stockQuantity(100)
                .imageUrl("http://example.com/image.jpg")
                .productCategory(productCategory)
                .seller(seller)
                .build();

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        ProductResponse response = productService.findById(1);

        assertNotNull(response);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    void testSave () {
        ProductRequest request = ProductRequest.builder()
                .name("Example Product")
                .description("This is an example product")
                .price(99.99)
                .stockQuantity(100)
                .imageUrl("http://example.com/image.jpg")
                .productCategoryId(1)
                .sellerId(1)
                .build();
        ProductCategory productCategory = ProductCategory.builder()
                .id(1)
                .name("Example Category")
                .build();

        Seller seller = Seller.builder()
                .id(1)
                .email("seller@example.com")
                .build();
        when(productCategoryRepository.findById(anyInt())).thenReturn(Optional.of(productCategory));
        when(sellerRepository.findById(anyInt())).thenReturn(Optional.of(seller));

        Product product = Product.builder()
                .id(1)
                .name("Example Product")
                .description("This is an example product")
                .price(99.99)
                .stockQuantity(100)
                .imageUrl("http://example.com/image.jpg")
                .productCategory(productCategory)
                .seller(seller)
                .build();
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse response = productService.save(request);

        assertNotNull(response);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdate () {
        ProductRequest request = ProductRequest.builder()
                .name("Example Product")
                .description("This is an example product")
                .price(99.99)
                .stockQuantity(100)
                .imageUrl("http://example.com/image.jpg")
                .productCategoryId(1)
                .sellerId(1)
                .build();

        ProductCategory productCategory = ProductCategory.builder()
                .id(1)
                .name("Example Category")
                .build();

        Seller seller = Seller.builder()
                .id(1)
                .email("seller@example.com")
                .build();

        Product product = Product.builder()
                .id(1)
                .name("Example Product")
                .description("This is an example product")
                .price(99.99)
                .stockQuantity(100)
                .imageUrl("http://example.com/image.jpg")
                .productCategory(productCategory)
                .seller(seller)
                .build();

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productCategoryRepository.findById(anyInt())).thenReturn(Optional.of(productCategory));
        when(sellerRepository.findById(anyInt())).thenReturn(Optional.of(seller));

        ProductResponse response = productService.update(1, request);

        assertNotNull(response);
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testDeleteById () {
        doNothing().when(productRepository).deleteById(anyInt());

        productService.deleteById(1);

        verify(productRepository, times(1)).deleteById(1);
    }
}