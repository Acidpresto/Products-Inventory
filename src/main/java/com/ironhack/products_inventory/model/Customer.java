package com.ironhack.products_inventory.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  Customer extends User{

    private String customerName;
    private String address;
    private Integer age;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SalesOrder> salesOrders;

    public Customer(String customerName, String address, Integer age) {
        this.customerName = customerName;
        this.address = address;
        this.age = age;
    }

    public Customer(String name, String username, String password, String customerName, String address, Integer age) {
        super(name, username, password);
        this.customerName = customerName;
        this.address = address;
        this.age = age;
    }


}
