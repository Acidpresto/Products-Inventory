package com.ironhack.products_inventory.dto;

import com.ironhack.products_inventory.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateStatusDTO {
    private OrderStatus status;
}
