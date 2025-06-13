package com.ironhack.products_inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private List<String> roles;
    private String password;
    private String name;
    private List<Long> roleIds;

    public UserDTO(Long id, String username, List<String> roles, String password, String name) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.name = name;
    }
}
