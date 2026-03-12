package com.aagam.product.repository;

import com.aagam.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Search products by partial name (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String search);

    // Check if a product with the exact name exists (case-insensitive)
    boolean existsByNameIgnoreCase(String name);

    // Fetch all products with names in the given list
    List<Product> findAllByNameIn(List<String> names);
}