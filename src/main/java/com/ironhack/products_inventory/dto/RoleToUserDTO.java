package com.ironhack.products_inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DATA TRANSFER OBJECT (DTO) PASS INFO NEW ROLE TO USER
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleToUserDTO {

    private String username;
    private String roleName;
}
