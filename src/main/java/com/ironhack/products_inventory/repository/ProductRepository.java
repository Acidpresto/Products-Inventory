package com.ironhack.products_inventory.repository;

import com.ironhack.products_inventory.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

List <Products> findByProductName(String productName);

List<Products> findByProductNameContaining(String productName);


}
