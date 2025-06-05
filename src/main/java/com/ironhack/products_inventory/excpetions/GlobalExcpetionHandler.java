package com.ironhack.products_inventory.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcpetionHandler {

    @ExceptionHandler(ProductNotFoundExcpetion.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundExcpetion ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); }
     //TODO review 8.1 class to add things add BAD REQUEST
}
