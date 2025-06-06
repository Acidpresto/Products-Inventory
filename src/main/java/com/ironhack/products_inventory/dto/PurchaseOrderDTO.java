package com.ironhack.products_inventory.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.enums.OrderStatus;
import com.ironhack.products_inventory.service.OrderService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO {
    //WE CAN'T EDIT THE ID
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    //add a constraint minimum date
    private LocalDate orderDate;
    private OrderStatus status;
    @NotNull
    private OrderType origin;
    @NotBlank
    private String supplierName;
    private List<OrderProductDTO> products;


    public PurchaseOrderDTO(Long id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }
}
