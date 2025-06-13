package com.ironhack.products_inventory.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.enums.OrderStatus;
import com.ironhack.products_inventory.service.OrderService;
import jakarta.validation.constraints.NotBlank;
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
@NoArgsConstructor
public class PurchaseOrderDTO {
    //WE CAN'T EDIT THE ID
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    //DEFAULT IS NOW
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate orderDate = LocalDate.now();

    //DEFAULT IS NOT PAYED
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderStatus status = OrderStatus.PENDING_PAYMENT;

    //DEFAULT PURCHASE
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderType origin = OrderType.PURCHASE;

    @NotNull
    private Long supplierId;

    //WE ONLY USE IT ON THE ANSWER
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String companyName;

    @NotEmpty
    private List<OrderProductDTO> products;

    public PurchaseOrderDTO(Long id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public PurchaseOrderDTO(Long id, LocalDate orderDate, OrderStatus status, OrderType origin, Long supplierId, String companyName, List<OrderProductDTO> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.origin = origin;
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.products = products;
    }


}
