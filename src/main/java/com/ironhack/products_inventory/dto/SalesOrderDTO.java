package com.ironhack.products_inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.enums.OrderStatus;
import com.ironhack.products_inventory.model.User;
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
    private Long customerId;
    private List<OrderProductDTO> products;
    private String customerFirstName;
    private String customerAddress;
    private Integer customerAge;
    private User customerUser;


    public SalesOrderDTO(Long id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public SalesOrderDTO(Long id, LocalDate orderDate, OrderStatus status, OrderType origin,
                         String customerFirstName, String customerAddress, Integer customerAge,
                         User customerUser, List<OrderProductDTO> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.origin = origin;
        this.customerFirstName = customerFirstName;
        this.customerAddress = customerAddress;
        this.customerAge = customerAge;
        this.customerUser = customerUser;
        this.products = products;
    }

    public SalesOrderDTO(Long id, LocalDate orderDate, OrderStatus status, OrderType origin, Long customerId, List<OrderProductDTO> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.origin = origin;
        this.customerId = customerId;
        this.products = products;
    }
}

