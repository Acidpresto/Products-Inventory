package com.ironhack.products_inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate orderDate = LocalDate.now();

    //DEFAULT PENDING_PAYMENT
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderStatus status = OrderStatus.PENDING_PAYMENT;

    //DEFAULT SALES
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderType origin = OrderType.SALES;

    @NotNull
    private Long customerId;

    //WE ONLY USE IT ON THE ANSWER
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String customerName;
    //WE ONLY USE IT ON THE ANSWER
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String customerAddress;

    //TBD WHERE TO USE IT
    private Integer customerAge;

    @NotEmpty
    private List<OrderProductDTO> products;


    public SalesOrderDTO(Long id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public SalesOrderDTO(Long id, LocalDate orderDate, OrderStatus status, OrderType origin, Long customerId, String customerName, String customerAddress, List<OrderProductDTO> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.origin = origin;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.products = products;
    }


}

