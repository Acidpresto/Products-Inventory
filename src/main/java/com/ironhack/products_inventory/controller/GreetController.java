package com.ironhack.products_inventory.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greet")
public class GreetController {

    @GetMapping
    public String greet() {
        return "Hello, World!";
    }


    @GetMapping("/personal")
    public String greetPersonal() {
        // Útil si quiero traerme o hacer cosas relacionadas SOLO al usuario que está logeado (sin que me tenga que mandar userId pe)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return "Hello, " + username;
    }
}
//Todo delete this class is for testing proposes
