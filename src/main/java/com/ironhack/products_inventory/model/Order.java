package com.ironhack.products_inventory.model;


import com.ironhack.products_inventory.enums.OrderOrigin;
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

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private OrderOrigin origin;

    //PRODUCTS AND ORDERS HAVE A RELATION MANY TO MANY SO,
    //WE HAVE CREATED AN INTERMEDIATE CLASS (ORDERSAFE)
    //TO SAVE MORE THAN ONE PRODUCT IN ONE ORDER
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderSafe> orderSafes;

    //WE CREATE AN ABSTRACT METHOD TO RETURN THE PREFIXED ID: to differentiate between Sales(S) and Purchases(P)
    public abstract String getDisplayId();

    public Order(LocalDate orderDate, OrderStatus status, OrderOrigin origin, List<OrderSafe> orderSafes) {
        this.orderDate = orderDate;
        this.status = status;
        this.origin = origin;
        this.orderSafes = orderSafes;
    }
}
