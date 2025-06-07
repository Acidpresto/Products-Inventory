package com.ironhack.products_inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDTO {
    //WE CAN'T EDIT THE ID
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    //DEFAULT NOW
    private LocalDate orderDate = LocalDate.now();
    //DEFAULT PENDING_PAYMENT
    private OrderStatus status = OrderStatus.PENDING_PAYMENT;
    //DEFAULT SALES
    private OrderType origin = OrderType.SALES;
    @NotBlank
    private String customerName;
    @NotBlank
    private String customerAddress;
    @NotEmpty
    private List<OrderProductDTO> products;

    public SalesOrderDTO(Long id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }
}
