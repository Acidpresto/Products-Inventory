package com.ironhack.products_inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductsInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsInventoryApplication.class, args);
    }

}


//TODO:
// + USER - CREATE assign roles based on what they can do ROLE_CUSTOMER, ROLE_SUPPLIER
// + ROLE - CREATE
// +CLEAN PRODUCT CONTROLLER
// +ONCE CHANGE THE SALES TO DELIVERED - "SEND MESSAGE PERSONALIZED ONCE CHANGE STATUS"
// +ADD A MESSAGE WITH THE AMOUNT EARNED * 21% IVA ONCE THE ORDER IS PAYED
// +IF I HAVE TIME CREATE A STORAGE FOR USERS (SUPPLIERS AND CUSTOMERS)
// +ADD SECURITY FOR PASSWORD
// +WRITE AND READ TXT
// + CREATE DIAGRAM CLASS