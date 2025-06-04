package com.ironhack.products_inventory.service;

import com.ironhack.products_inventory.dto.PurchaseOrderDTO;
import com.ironhack.products_inventory.model.OrderSafe;
import com.ironhack.products_inventory.model.Product;
import com.ironhack.products_inventory.model.PurchaseOrder;
import com.ironhack.products_inventory.repository.OrdersRepository;
import com.ironhack.products_inventory.repository.ProductRepository;
import com.ironhack.products_inventory.repository.PurchaseOrderRepository;
import com.ironhack.products_inventory.repository.SalesOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {


    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SalesOrderRepository salesOrderRepository;
    private final ProductRepository productRepository;

    public OrderService(PurchaseOrderRepository purchaseOrderRepository, SalesOrderRepository salesOrderRepository, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.salesOrderRepository = salesOrderRepository;
    }

    //CREATE A NEW ORDER PURCHASE
    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO dto) {

        //FIRST WE CONVERT THE DTO PRODUCTS TO ORDER-SAFE. AND VALIDATE ID
        List<OrderSafe> orderSafes = dto.getProducts().stream().map(item -> {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not valid"));//TODO NEW BAD REQUEST EXCEPTION

            OrderSafe os = new OrderSafe();
            os.setProduct(product);
            os.setQuantityOrdered(item.getQuantityOrdered());
            return os;
        }).toList();

        //CREATE A NEW PURCHASE-ORDER FROM DTO VALUES
        PurchaseOrder order = new PurchaseOrder(
                dto.getOrderDate(),
                dto.getStatus(),
                dto.getOrigin(),
                orderSafes,
                dto.getSupplierName()
        );
        //LINK THE ORDER-SAFE WITH ORDER, THAT'S MANY TO ONE. EACH PRODUCT HAS AN ORDER
        orderSafes.forEach(os -> {os.setOrder(order);});
        //SAVE THE ORDER
        return (PurchaseOrder) purchaseOrderRepository.save(order);
    }




}
