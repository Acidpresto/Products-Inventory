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

    private Long productId;
    private String productName;
    @NotNull
    private Integer quantityOrdered;

    //CONSTRUCTOR WITHOUT NAME
    public OrderProductDTO(Long productId, Integer quantityOrdered) {
        this.productId = productId;
        this.quantityOrdered = quantityOrdered;
    }
}
