package com.ironhack.products_inventory.excpetions;

public class DuplicateProductExcpetion extends RuntimeException {
    public DuplicateProductExcpetion(String message) {
        super(message);
    }
}
