package com.ironhack.products_inventory.controller;

import com.ironhack.products_inventory.dto.RoleToUserDTO;
import com.ironhack.products_inventory.model.Role;
import com.ironhack.products_inventory.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRole(@RequestBody Role role) {
        roleService.save(role);
    }

    //ADD A NEW ROLE TO CURRENT USER
    @PostMapping("/roles/add-to-user")
    @ResponseStatus(HttpStatus.OK)
    public void addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        roleService.addRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }
}