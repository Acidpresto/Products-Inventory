package com.ironhack.products_inventory.model;

import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "order_id")
public class SalesOrder extends Order {

    public SalesOrder(LocalDate orderDate, OrderStatus status, OrderType origin, List<OrderSafe> orderSafes, Customer customer) {
        super(orderDate, status, origin, orderSafes);
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "cutomer_id")
    private Customer customer;

}
