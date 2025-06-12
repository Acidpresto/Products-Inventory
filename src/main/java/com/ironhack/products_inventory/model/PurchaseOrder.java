package com.ironhack.products_inventory.model;

import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.persistence.*;
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

    public PurchaseOrder(LocalDate orderDate, OrderStatus status, OrderType origin, List<OrderSafe> orderSafes, Supplier supplier) {
        super(orderDate, status, origin, orderSafes);
        this.supplier = supplier;
    }

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
