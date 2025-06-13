package com.ironhack.products_inventory.excpetions;

public class UserNotFoundExcpetion extends RuntimeException {
    public UserNotFoundExcpetion(String message) {
        super(message);
    }
}
