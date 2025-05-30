package com.ironhack.products_inventory.repository;

import com.ironhack.products_inventory.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}


