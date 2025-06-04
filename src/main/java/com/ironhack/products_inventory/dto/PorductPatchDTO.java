package com.ironhack.products_inventory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PorductPatchDTO {

    @NotNull
    private String productName;
    private String description;
    private Integer price;
    private Integer minQuantity;
    private Integer stock;
}
