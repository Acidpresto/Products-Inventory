package com.ironhack.products_inventory.controller;

import com.ironhack.products_inventory.dto.PorductPatchDTO;
import com.ironhack.products_inventory.dto.ProductDTO;
import com.ironhack.products_inventory.model.Product;
import com.ironhack.products_inventory.service.ProductService;
import org.springframework.http.ResponseEntity;
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
    public List<ProductDTO> findAll() {
        return productService.findAll();
    }

    @GetMapping("/name-contains")
    public List<Product> findByNameContains(@RequestParam String productName) {
        return productService.findByProductNameContaining(productName);
    }

    @GetMapping("/name")
    public List<Product> findByName(@RequestParam String productName) {
        return productService.findByName(productName);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete (@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody PorductPatchDTO dto) {
        return ResponseEntity.ok(productService.patchProduct(id, dto));
    }
}
