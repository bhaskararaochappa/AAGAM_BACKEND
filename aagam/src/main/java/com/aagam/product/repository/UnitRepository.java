package com.aagam.product.repository;

import com.aagam.product.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    // Find unit by name
    Optional<Unit> findByName(String name);

    // Check if unit with given name exists
    boolean existsByNameIgnoreCase(String name);
}