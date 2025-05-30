package com.ironhack.products_inventory.service;

import com.ironhack.products_inventory.dto.PurchaseOrderDTO;
import com.ironhack.products_inventory.model.OrderSafe;
import com.ironhack.products_inventory.model.Products;
import com.ironhack.products_inventory.model.PurchaseOrder;
import com.ironhack.products_inventory.repository.OrdersRepository;
import com.ironhack.products_inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    public OrderService(OrdersRepository ordersRepository, ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
    }

    //CREATE A NEW ORDER PURCHASE
    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO dto) {

        //FIRST WE CONVERT THE DTO PRODUCTS TO ORDER-SAFE
        List<OrderSafe> orderSafes = dto.getProducts().stream().map(item -> {
            Products product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not valid"));

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
        return (PurchaseOrder) ordersRepository.save(order);
    }




}
