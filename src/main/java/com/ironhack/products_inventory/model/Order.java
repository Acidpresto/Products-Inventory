package com.ironhack.products_inventory.model;


import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "orders")
public abstract class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)//PAYED,PENDING_PAYMENT, PREPARED, SHIPPED, DELIVERED
    private OrderStatus status;

    @Enumerated(EnumType.STRING) //PURCHASE or SALE
    private OrderType type;

    //PRODUCTS AND ORDERS HAVE A RELATION MANY TO MANY SO,
    //WE HAVE CREATED AN INTERMEDIATE CLASS (ORDER-SAFE)
    //TO SAVE MORE THAN ONE PRODUCT IN ONE ORDER
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderSafe> orderSafes;


    public Order(LocalDate orderDate, OrderStatus status, OrderType origin, List<OrderSafe> orderSafes) {
        this.orderDate = orderDate;
        this.status = status;
        this.type = origin;
        this.orderSafes = orderSafes;
    }
}
