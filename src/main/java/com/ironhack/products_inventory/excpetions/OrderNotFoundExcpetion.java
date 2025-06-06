package com.ironhack.products_inventory.excpetions;

public class OrderNotFoundExcpetion extends RuntimeException {
    public OrderNotFoundExcpetion(String message) {
        super(message);
    }
}
