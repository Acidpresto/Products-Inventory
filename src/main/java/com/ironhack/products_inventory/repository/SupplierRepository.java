package com.ironhack.products_inventory.repository;

import com.ironhack.products_inventory.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository <Supplier, Long> {
    Supplier findByCompanyName(String companyName);
}
