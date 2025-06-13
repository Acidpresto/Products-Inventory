package com.ironhack.products_inventory.controller;

import com.ironhack.products_inventory.dto.*;
import com.ironhack.products_inventory.model.PurchaseOrder;
import com.ironhack.products_inventory.model.SalesOrder;
import com.ironhack.products_inventory.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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


    @GetMapping("/all/purchase")
    public List<PurchaseOrderDTO> findAllPurchaseOrders() {
        return orderService.findPurchaseOrders();
    }

    @PatchMapping("/{id}/purchase/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody UpdateStatusDTO dto) {
        String resultMessage = orderService.updatePurchaseStatus(id, dto.getStatus());
        return ResponseEntity.ok(resultMessage);
    }

    @PutMapping("/new/purchase")
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@Valid @RequestBody PurchaseOrderDTO dto) {
        PurchaseOrderDTO response = orderService.createPurchaseOrderDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/all/sales")
    public List <SalesOrderDTO> findAllSalesOrders() {
        return orderService.findSaleOrders();
    }

    @PatchMapping("/{id}/sales/status")
    public ResponseEntity<SalesOrderDTO> updateOrderStatus2(@PathVariable Long id, @RequestBody SalesOrderDTO dto) {
        SalesOrder updated = orderService.updateSalesStatus(id, dto.getStatus());
        return ResponseEntity.ok(new SalesOrderDTO(updated.getOrderId(), updated.getStatus()));
    }


    @PutMapping("/new/sales")
    public ResponseEntity<SalesOrderDTO> createSalesOrderDTO(@Valid @RequestBody SalesOrderDTO dto) {
        SalesOrderDTO response = orderService.createSalesOrderDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
