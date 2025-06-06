package com.ironhack.products_inventory.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO {
    @NotNull
    //no negative
    private Long productId;
    private String productName;
    //Add a validation to be positive
    private Integer quantityOrdered;
}
