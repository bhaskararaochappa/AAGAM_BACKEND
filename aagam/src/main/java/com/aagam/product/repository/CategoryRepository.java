package com.aagam.product.repository;

import com.aagam.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find category by name (used in ProductService)
    Optional<Category> findByName(String name);

    // Check if category with the given name exists
    boolean existsByNameIgnoreCase(String name);
}