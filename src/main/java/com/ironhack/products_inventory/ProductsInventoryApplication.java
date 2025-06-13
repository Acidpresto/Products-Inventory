package com.ironhack.products_inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ProductsInventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductsInventoryApplication.class, args);
    }

}

