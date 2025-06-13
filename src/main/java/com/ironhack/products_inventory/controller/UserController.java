package com.ironhack.products_inventory.controller;

import com.ironhack.products_inventory.dto.SupplierDTO;
import com.ironhack.products_inventory.dto.UserDTO;
import com.ironhack.products_inventory.model.Supplier;
import com.ironhack.products_inventory.model.User;
import com.ironhack.products_inventory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.getUser(username);
        return ResponseEntity.ok(userDTO);
    }


    @PostMapping("/supplier")
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierDTO saveSupplierDTO(@RequestBody SupplierDTO supplierDTO) {
        return userService.saveSupplierDTO(supplierDTO);
    }
}
