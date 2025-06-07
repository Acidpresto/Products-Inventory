package com.ironhack.products_inventory.model;

import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.enums.OrderStatus;
import jakarta.persistence.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "order_id")
public class SalesOrder extends Order {
    @NotBlank
    private String customerName;
    private String customerAddress;

    public SalesOrder(LocalDate orderDate, OrderStatus status, OrderType origin, List<OrderSafe> orderSafes, String customerName, String customerAddress) {
        super(orderDate, status, origin, orderSafes);
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    @Override
    public String getDisplayId(){
        return "S" + getOrderId();
    }
}
