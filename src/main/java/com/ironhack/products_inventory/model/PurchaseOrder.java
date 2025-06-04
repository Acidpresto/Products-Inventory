package com.ironhack.products_inventory.model;

import com.ironhack.products_inventory.enums.OrderOrigin;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "order_id")
public class PurchaseOrder extends Order {

    private String supplierName;

    public PurchaseOrder(LocalDate orderDate, OrderStatus status, OrderOrigin origin, List<OrderSafe> orderSafes, String supplierName) {
        super(orderDate, status, origin, orderSafes);
        this.supplierName = supplierName;
    }

    @Override
    public String getDisplayId(){
        return "P" + getOrderId();
    }
}
