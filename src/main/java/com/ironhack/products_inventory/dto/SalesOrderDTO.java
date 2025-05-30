package com.ironhack.products_inventory.dto;

import com.ironhack.products_inventory.enums.OrderOrigin;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SalesOrderDTO {
    private LocalDate orderDate;
    private OrderStatus status;
    private OrderOrigin origin;
    @NotBlank
    private String customerName;
    @NotBlank
    private String customerAddress;
    private List<OrderProductDTO> products;

}
