package com.markin.testcontainerstalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.markin.testcontainerstalk.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Finds a Product by its name.
     * @param name The name of the product to find.
     * @return An Optional containing the Product if found, or empty otherwise.
     */
    Optional<Product> findByName(String name);
}
