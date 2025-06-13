package com.ironhack.products_inventory.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExcpetionHandler {

    @ExceptionHandler(ProductNotFoundExcpetion.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundExcpetion ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(OrderNotFoundExcpetion.class)
    public ResponseEntity<String> handleOrderNotFound(OrderNotFoundExcpetion ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(UserNotFoundExcpetion.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundExcpetion ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateProductExcpetion.class)
    public ResponseEntity<String> handleDuplicateProduct(DuplicateProductExcpetion ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    //ADD ALL THE BAD REQUESTS MESSAGES
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}



    //TODO review 8.1 class to add things add BAD REQUEST

