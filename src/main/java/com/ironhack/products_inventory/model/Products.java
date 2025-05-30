package com.ironhack.products_inventory.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private String description;
    private int price;
    private int min_quantity;
    private int stock;

    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
    private List <OrderSafe> orderSafes;
}
