package com.ironhack.products_inventory.repository;

import com.ironhack.products_inventory.model.Order;
import com.ironhack.products_inventory.model.PurchaseOrder;
import jakarta.persistence.Inheritance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
