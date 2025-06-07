package com.ironhack.products_inventory.controller;

import com.ironhack.products_inventory.dto.OrderProductDTO;
import com.ironhack.products_inventory.dto.ProductDTO;
import com.ironhack.products_inventory.dto.PurchaseOrderDTO;
import com.ironhack.products_inventory.dto.SalesOrderDTO;
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
    public ResponseEntity<PurchaseOrderDTO> updateOrderStatus(@PathVariable Long id, @RequestBody PurchaseOrderDTO dto) {
        PurchaseOrder updated = orderService.updatePurchaseStatus(id, dto.getStatus());
        return ResponseEntity.ok(new PurchaseOrderDTO(updated.getOrderId(), updated.getStatus()));
    }

    @PutMapping("/new/purchase")
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@Valid @RequestBody PurchaseOrderDTO dto) {
        PurchaseOrder created = orderService.createPurchaseOrder(dto);

        PurchaseOrderDTO response = new PurchaseOrderDTO(
                created.getOrderId(),
                created.getOrderDate(),
                created.getStatus(),
                created.getType(),
                created.getSupplierName(),
                created.getOrderSafes().stream().map(os -> new OrderProductDTO(
                        os.getProduct().getProductId(),
                        os.getQuantityOrdered()
                )).toList());
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
    public ResponseEntity<SalesOrderDTO> createSalesOrder(@Valid @RequestBody SalesOrderDTO dto) {
        SalesOrder created = orderService.createSalesOrder(dto);

        SalesOrderDTO response = new SalesOrderDTO(
                created.getOrderId(),
                created.getOrderDate(),
                created.getStatus(),
                created.getType(),
                created.getCustomerName(),
                created.getCustomerAddress(),
                created.getOrderSafes().stream().map(os-> new OrderProductDTO(
                        os.getProduct().getProductId(),
                        os.getQuantityOrdered()
                )).toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
