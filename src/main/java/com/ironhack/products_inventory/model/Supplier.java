package com.ironhack.products_inventory.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@AllArgsConstructor
@Setter
public class Supplier extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String companyAddress;


    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<PurchaseOrder> purchaseOrders;

    public Supplier(String companyName, String companyAddress) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    public Supplier(String name, String username, String password, String companyName, String companyAddress) {
        super(name, username, password);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }
}
