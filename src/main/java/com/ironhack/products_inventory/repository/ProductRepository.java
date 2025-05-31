package com.ironhack.products_inventory.repository;

import com.ironhack.products_inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

List <Product> findByProductName(String productName);

List<Product> findByProductNameContaining(String productName);


}
