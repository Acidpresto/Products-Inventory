package com.ironhack.products_inventory.model;

import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "order_id")
public class PurchaseOrder extends Order {

    private String supplierName;

    public PurchaseOrder(LocalDate orderDate, OrderStatus status, OrderType origin, List<OrderSafe> orderSafes, String supplierName) {
        super(orderDate, status, origin, orderSafes);
        this.supplierName = supplierName;
    }

    @Override
    public String getDisplayId(){
        return "P" + getOrderId();
    }
}
