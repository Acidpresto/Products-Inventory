package com.ironhack.products_inventory.controller;

import com.ironhack.products_inventory.model.Products;
import com.ironhack.products_inventory.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Products> findAll() {
        return productService.findAll();
    }

    @GetMapping("/name-contains")
    public List<Products> findByNameContains(@RequestParam String productName) {
        return productService.findByProductNameContaining(productName);
    }

    @GetMapping("/name")
    public List<Products> findByName(@RequestParam String productName) {
        return productService.findByName(productName);
    }

    @GetMapping("/{id}")
    public Products findById(@PathVariable Long id) {
        return productService.findById(id);
    }
}
