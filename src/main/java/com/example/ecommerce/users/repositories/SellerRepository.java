package com.example.ecommerce.users.repositories;

import com.example.ecommerce.users.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findByEmail(String email);
}
