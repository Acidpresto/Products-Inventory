package com.ironhack.products_inventory.repository;

import com.ironhack.products_inventory.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

    //TODO REMOVE IT IF NO NEED!!
}


