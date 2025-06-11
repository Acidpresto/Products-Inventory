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
// +ONCE CHANGE THE SALES TO DELIVERED - "SEND MESSAGE PERSONALIZED ONCE CHANGE STATUS"
// +ADD A MESSAGE WITH THE AMOUNT EARNED * 21% IVA ONCE THE ORDER IS PAYED
// +CHANGE ORDER STATUS IN 10 seconds as trial
// +WRITE AND READ TXT
// + CREATE DIAGRAM CLASS