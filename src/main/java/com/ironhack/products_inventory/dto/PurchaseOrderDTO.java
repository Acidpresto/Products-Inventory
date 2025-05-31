package com.ironhack.products_inventory.dto;


import com.ironhack.products_inventory.enums.OrderOrigin;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PurchaseOrderDTO {
    //add a constraint minimum date
    private LocalDate orderDate;
    @NotNull
    private OrderStatus status;
    @NotNull
    private OrderOrigin origin;
    @NotBlank
    private String supplierName;
    private List<OrderProductDTO> products;
}
