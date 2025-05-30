package com.ironhack.products_inventory.dto;


import com.ironhack.products_inventory.enums.OrderOrigin;
import com.ironhack.products_inventory.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PurchaseOrderDTO {
    private LocalDate orderDate;
    private OrderStatus status;
    private OrderOrigin origin;
    private String supplierName;
    private List<OrderProductDTO> products;
}
