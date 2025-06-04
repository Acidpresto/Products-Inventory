package com.ironhack.products_inventory.excpetions;

public class ProductNotFoundExcpetion extends RuntimeException {
    public ProductNotFoundExcpetion(String message) {
        super(message);
    }
}
