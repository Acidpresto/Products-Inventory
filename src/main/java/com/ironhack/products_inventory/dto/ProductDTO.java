package com.ironhack.products_inventory.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Positive(message = "Price must be positive")
    private Integer price;
    @Min(value = 1, message = "Minim quantity must be at least on product")
    private Integer minQuantity;
    @Positive(message = "We can't have negative stock")
    private Integer stock;
}
