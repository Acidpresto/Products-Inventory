package com.ironhack.products_inventory.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductDTO {
    //WE CAN'T EDIT THE ID
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank(message = "Product name must not be blank")
    private String productName;
    @NotBlank(message = "description field is required")
    private String description;
    private Integer price;
    private Integer minQuantity;
    private Integer stock;
    //TODO add no negative price, min quantity higher than 1,
}
