package com.ironhack.products_inventory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.query.Order;

@Setter
@Getter
@Entity
public class OrderSafe {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int quantityOrdered;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product;
}
