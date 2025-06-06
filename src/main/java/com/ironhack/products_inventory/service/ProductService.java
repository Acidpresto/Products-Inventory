package com.ironhack.products_inventory.service;


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
    //FOR ALL THE METHODS WE CALL PRODUCT DTO CONSTRUCTOR WITHOUT THE ORDER-SAFE
    //FIND ALL PRODUCTS
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
    public List<ProductDTO> findByProductNameContaining(String productName) {
        return productRepository.findByProductNameContaining(productName).stream().map(p -> new ProductDTO (
                p.getProductId(),
                p.getProductName(),
                p.getDescription(),
                p.getPrice(),
                p.getMinQuantity(),
                p.getStock()))
                .collect(Collectors.toList());
    }

    //FIND BY ID
    public ProductDTO findById(Long productId) {
        return productRepository.findById(productId).map(p-> new ProductDTO (
                        p.getProductId(),
                        p.getProductName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getMinQuantity(),
                        p.getStock()))
                        .orElseThrow(() -> new ProductNotFoundExcpetion("Product with id " + productId + " not found"));
    }

    //FIND BY EXACT NAME
    public List<ProductDTO> findByName (String productName) {
        List<Product> products = productRepository.findByProductNameContaining(productName);

        if (products.isEmpty()) {
            throw new ProductNotFoundExcpetion("Product with name " + productName + " not found");
        }
        return products.stream().map(p-> new ProductDTO(
                p.getProductId(),
                p.getProductName(),
                p.getDescription(),
                p.getPrice(),
                p.getMinQuantity(),
                p.getStock()))
                .collect(Collectors.toList());
    }

    //DELETE PRODUCT
    public void deleteById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.delete(product);
        } else {
            throw new ProductNotFoundExcpetion("Product with ID " + productId + " not found");
        }
    }

    //UPDATE PRODUCTS DETAILS - (DESPITE ID THAT IS BLOCKED ON THE DTO) //
    public Product patchProduct (Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundExcpetion("Product with ID " + productId + " not found"));

         if (productDTO.getProductName() != null) {
             product.setProductName(productDTO.getProductName());
         }

         if (productDTO.getDescription() != null) {
             product.setDescription(productDTO.getDescription());
         }

         if (productDTO.getPrice() != null){
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

    //CREATE NEW PRODUCT
    public Product createProduct (ProductDTO productDTO) {
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setMinQuantity(productDTO.getMinQuantity());
        product.setStock(productDTO.getStock());

        return productRepository.save(product);
    }
}
