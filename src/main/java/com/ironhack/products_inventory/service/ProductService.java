package com.ironhack.products_inventory.service;


import com.ironhack.products_inventory.model.Products;
import com.ironhack.products_inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //FIND ALL PRODUCTS
    public List<Products> findAll() {
        return productRepository.findAll();
    }

    //FIND BY WORD on the NAME
    public List<Products> findByProductNameContaining(String productName) {
        return productRepository.findByProductNameContaining(productName);
    }

    //FIND BY ID
    public Products findById(Long productId) {
        return productRepository.findById(productId).get();
    }

    //FIND BY EXACT NAME
    public List<Products> findByName (String productName) {
        return productRepository.findByProductName(productName);
    }
}
