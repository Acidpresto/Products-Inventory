package com.ironhack.products_inventory.controller;

import com.ironhack.products_inventory.dto.ProductDTO;
import com.ironhack.products_inventory.model.Product;
import com.ironhack.products_inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
        return productService.findByProductName(productName);
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
    public ResponseEntity<ProductDTO> update(@Valid @PathVariable Long id, @RequestBody ProductDTO dto) {
        Product updatedProduct = productService.patchProduct(id, dto);
        ProductDTO responseDTO = new ProductDTO(
                updatedProduct.getProductId(),
                updatedProduct.getProductName(),
                updatedProduct.getDescription(),
                updatedProduct.getPrice(),
                updatedProduct.getMinQuantity(),
                updatedProduct.getStock());

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/new-product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO dto) {
        Product savedProduct = productService.createProduct(dto);

        ProductDTO responseDTO = new ProductDTO(
                savedProduct.getProductId(),
                savedProduct.getProductName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getMinQuantity(),
                savedProduct.getStock()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
