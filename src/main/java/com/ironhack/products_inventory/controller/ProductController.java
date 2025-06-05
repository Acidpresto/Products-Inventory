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
    public List<ProductDTO> findByNameContains(@RequestParam String productName) {
        return productService.findByProductNameContaining(productName);
    }

    @GetMapping("/name")
    public List<ProductDTO> findByName(@RequestParam String productName) {
        return productService.findByName(productName);
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok("Product with id: " + id + " has been deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody PorductPatchDTO dto) {
        return ResponseEntity.ok(productService.patchProduct(id, dto));
    }
}
