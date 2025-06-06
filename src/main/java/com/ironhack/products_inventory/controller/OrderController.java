package com.ironhack.products_inventory.controller;

import com.ironhack.products_inventory.dto.ProductDTO;
import com.ironhack.products_inventory.dto.PurchaseOrderDTO;
import com.ironhack.products_inventory.model.PurchaseOrder;
import com.ironhack.products_inventory.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/purchase/all")
    public List<PurchaseOrderDTO> findAll() {
        return orderService.findAllOrders();
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<PurchaseOrderDTO> updateOrderStatus(@PathVariable Long id, @RequestBody PurchaseOrderDTO dto) {
        PurchaseOrder updated = orderService.updateOrderStatus(id, dto.getStatus());
        return ResponseEntity.ok(new PurchaseOrderDTO(updated.getOrderId(), updated.getStatus()));
    }


}
