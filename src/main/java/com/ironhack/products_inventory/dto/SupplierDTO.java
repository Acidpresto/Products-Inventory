package com.ironhack.products_inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private Long id;
    private String username;
    private String name;
    private String password;
    private String companyName;
    private String companyAddress;

    //IN CASE WE WON'T  RETURN PASSWORD - DESPITE WE ALWAYS RETURN NULL
    public SupplierDTO(Long id, String username, String name, String companyName, String companyAddress) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.companyName = companyName;
        this.companyAddress = companyAddress;

    }
}
