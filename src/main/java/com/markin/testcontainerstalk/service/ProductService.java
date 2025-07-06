package com.markin.testcontainerstalk.service;

import com.markin.testcontainerstalk.model.Product;
import com.markin.testcontainerstalk.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a product by its ID.
     * @param id The ID of the product.
     * @return An Optional containing the product if found, or empty otherwise.
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Retrieves all products.
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its name.
     * @param name The name of the product.
     * @return An Optional containing the product if found, or empty otherwise.
     */
    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

}
