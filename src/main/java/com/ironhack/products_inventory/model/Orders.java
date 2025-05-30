package com.ironhack.products_inventory.model;


import com.ironhack.products_inventory.enums.OrderOrigin;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private int quantityOrdered;
    @Enumerated(EnumType.STRING)
    private OrderOrigin origin;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    //WE CREATE AN ABSTRACT METHOD TO RETURN THE PREFIXED ID: to differentiate between Sales(S) and Purchases(P)
    public abstract String getDisplayId();

}
