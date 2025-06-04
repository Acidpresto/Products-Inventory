package com.ironhack.products_inventory.service;


import com.ironhack.products_inventory.dto.PorductPatchDTO;
import com.ironhack.products_inventory.dto.ProductDTO;
import com.ironhack.products_inventory.excpetions.ProductNotFoundExcpetion;
import com.ironhack.products_inventory.model.Product;
import com.ironhack.products_inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //FIND ALL PRODUCTS - WE CALL PRODUCT DTO CONSTRUCTOR WITHOUT THE ORDER-SAFE
    public List<ProductDTO> findAll() {

        return productRepository.findAll().stream().map(p -> new ProductDTO (
                p.getProductId(),
                p.getProductName(),
                p.getDescription(),
                p.getPrice(),
                p.getMinQuantity(),
                p.getStock()))
                .collect(Collectors.toList());
    }

    //FIND BY WORD on the NAME
    public List<Product> findByProductNameContaining(String productName) {
        return productRepository.findByProductNameContaining(productName);
    }

    //FIND BY ID
    public Product findById(Long productId) {
        return productRepository.findById(productId).get();
    }

    //FIND BY EXACT NAME
    public List<Product> findByName (String productName) {
        return productRepository.findByProductName(productName);
    }

    //DELETE PRODUCT
    public Product deleteById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.delete(product);
            return product;
        } else {
            throw new ProductNotFoundExcpetion("Product with ID " + productId + " not found");
        }
    }

    //UPDATE PRODUCTS DETAILS - (DESPITE ID)
    public Product patchProduct (Long productId, PorductPatchDTO productDTO){
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundExcpetion("Product with ID " + productId + " not found"));

         if (productDTO.getProductName() != null) {
             product.setProductName(productDTO.getProductName());
         }

         if (productDTO.getPrice() != null) {
             product.setPrice(productDTO.getPrice());
         }

         if (productDTO.getMinQuantity() != null) {
             product.setMinQuantity(productDTO.getMinQuantity());
         }

         if (productDTO.getStock() != null) {
             product.setStock(productDTO.getStock());
         }

         return productRepository.save(product);
    }
}
