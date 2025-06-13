package com.ironhack.products_inventory.dto;

import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateStatusDTO {

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
