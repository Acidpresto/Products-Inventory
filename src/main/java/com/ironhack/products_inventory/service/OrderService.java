package com.ironhack.products_inventory.service;

import com.ironhack.products_inventory.dto.OrderProductDTO;
import com.ironhack.products_inventory.dto.ProductDTO;
import com.ironhack.products_inventory.dto.PurchaseOrderDTO;
import com.ironhack.products_inventory.enums.OrderStatus;
import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.excpetions.OrderNotFoundExcpetion;
import com.ironhack.products_inventory.model.OrderSafe;
import com.ironhack.products_inventory.model.Product;
import com.ironhack.products_inventory.model.PurchaseOrder;
import com.ironhack.products_inventory.repository.OrdersRepository;
import com.ironhack.products_inventory.repository.ProductRepository;
import com.ironhack.products_inventory.repository.PurchaseOrderRepository;
import com.ironhack.products_inventory.repository.SalesOrderRepository;
import jakarta.transaction.Transactional;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderService {


    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SalesOrderRepository salesOrderRepository;
    private final ProductRepository productRepository;
    private final OrdersRepository ordersRepository;

    public OrderService(PurchaseOrderRepository purchaseOrderRepository, SalesOrderRepository salesOrderRepository, ProductRepository productRepository, OrdersRepository ordersRepository) {
        this.productRepository = productRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.salesOrderRepository = salesOrderRepository;
        this.ordersRepository = ordersRepository;
    }

    //GET ALL ORDERS
    public List<PurchaseOrderDTO> findAllOrders() {
        return purchaseOrderRepository.findAll().stream().map(p -> {
            List<OrderProductDTO> products = p.getOrderSafes().stream().map(orderSafe ->
                    new OrderProductDTO(
                            orderSafe.getProduct().getProductId(),
                            orderSafe.getProduct().getProductName(),
                            orderSafe.getQuantityOrdered()))
                            .collect(Collectors.toList());
                    return new PurchaseOrderDTO(
                        p.getOrderId(),
                        p.getOrderDate(),
                        p.getStatus(),
                        p.getType(),
                        p.getSupplierName(),
                        products);
        }).collect(Collectors.toList());
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

    //CHANGE STATUS TO PAYED -> INCREASE THE NUMBER OF STOCK OF THE PRODUCTS ORDER
    //@Transactional
    public PurchaseOrder updateOrderStatus(Long orderId, OrderStatus newStatus) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundExcpetion("Order not found with ID " + orderId));

        if (order.getType() == OrderType.PURCHASE
            &&  newStatus == OrderStatus.PAYED
            && order.getStatus() != OrderStatus.PAYED){

            for (OrderSafe orderSafe : order.getOrderSafes()) {
                Product product = orderSafe.getProduct();
                Integer quantityOrdered = orderSafe.getQuantityOrdered();

                //UPDATE STOCK
                product.setStock(product.getStock() + quantityOrdered);
                productRepository.save(product);
            }
        }

        order.setStatus(newStatus);
        return purchaseOrderRepository.save(order);


    }


    //TODO SALES CREATE
}
