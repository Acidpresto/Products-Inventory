package com.ironhack.products_inventory.repository;

import com.ironhack.products_inventory.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Long> {
    Customer findByCustomerName(String firstName);
}
