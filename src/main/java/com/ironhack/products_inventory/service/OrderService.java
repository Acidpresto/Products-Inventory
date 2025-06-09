package com.ironhack.products_inventory.service;

import com.ironhack.products_inventory.dto.OrderProductDTO;
import com.ironhack.products_inventory.dto.PurchaseOrderDTO;
import com.ironhack.products_inventory.dto.SalesOrderDTO;
import com.ironhack.products_inventory.enums.OrderStatus;
import com.ironhack.products_inventory.enums.OrderType;
import com.ironhack.products_inventory.excpetions.OrderNotFoundExcpetion;
import com.ironhack.products_inventory.excpetions.ProductNotFoundExcpetion;
import com.ironhack.products_inventory.model.OrderSafe;
import com.ironhack.products_inventory.model.Product;
import com.ironhack.products_inventory.model.PurchaseOrder;
import com.ironhack.products_inventory.model.SalesOrder;
import com.ironhack.products_inventory.repository.ProductRepository;
import com.ironhack.products_inventory.repository.PurchaseOrderRepository;
import com.ironhack.products_inventory.repository.SalesOrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    //GET PURCHASE ORDERS
    public List<PurchaseOrderDTO> findPurchaseOrders() {
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

    //CREATE A NEW PURCHASE ORDER
    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO dto) {

        //FIRST WE CONVERT THE DTO PRODUCTS TO ORDER-SAFE. AND VALIDATE ID
        List<OrderSafe> orderSafes = dto.getProducts().stream().map(item -> {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ProductNotFoundExcpetion("Invalid product ID: " + item.getProductId()));

            OrderSafe os = new OrderSafe();
            os.setProduct(product);
            os.setQuantityOrdered(item.getQuantityOrdered());
            return os;
        }).toList();

        //CREATE A NEW PURCHASE-ORDER FROM DTO VALUES
        PurchaseOrder order = new PurchaseOrder(
                LocalDate.now(),
                OrderStatus.PENDING_PAYMENT,
                OrderType.PURCHASE,
                orderSafes,
                dto.getSupplierName()
        );
        //LINK THE ORDER-SAFE WITH ORDER, THAT'S MANY TO ONE. EACH PRODUCT HAS AN ORDER, AND SAVE IT
        orderSafes.forEach(os -> {os.setOrder(order);});
        return (PurchaseOrder) purchaseOrderRepository.save(order);
    }

    //PURCHASE CHANGE STATUS TO PAYED -> INCREASE THE NUMBER OF STOCK OF THE PRODUCTS ORDER
    @Transactional //TO  ENSURE ALL PRODUCT HAVE SUCCESS IF NO REVERT BACK
    public PurchaseOrder updatePurchaseStatus(Long orderId, OrderStatus newStatus) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundExcpetion("Order not found with ID " + orderId));

        if (order.getType() == OrderType.PURCHASE
            &&  newStatus == OrderStatus.PAYED
            && order.getStatus() != OrderStatus.PAYED){
            log.info("Order status updated to " + newStatus);

            LocalDateTime date = LocalDateTime.now();
// variable localDate.now()
            for (OrderSafe orderSafe : order.getOrderSafes()) {
                Product product = orderSafe.getProduct();
                Integer quantityOrdered = orderSafe.getQuantityOrdered();

                //UPDATE STOCK
                product.setStock(product.getStock() + quantityOrdered);
                productRepository.save(product);
            }

 // if localDate variable han pasado -- para la demo diría 1 min/ 30 seg -- actualizas el estado 1º a uno y haces un log en terminal en cada camnio
        log.info("Order status updated to " + newStatus);
        }

        order.setStatus(newStatus);
        return purchaseOrderRepository.save(order);


    }

    //GET PURCHASE ORDERS
    public List<SalesOrderDTO> findSaleOrders() {
        return salesOrderRepository.findAll().stream().map(p -> {
            List<OrderProductDTO> products = p.getOrderSafes().stream().map(orderSafe ->
                            new OrderProductDTO(
                                    orderSafe.getProduct().getProductId(),
                                    orderSafe.getProduct().getProductName(),
                                    orderSafe.getQuantityOrdered()))
                    .collect(Collectors.toList());
            return new SalesOrderDTO(
                    p.getOrderId(),
                    p.getOrderDate(),
                    p.getStatus(),
                    p.getType(),
                    p.getCustomerName(),
                    p.getCustomerAddress(),
                    products);
        }).collect(Collectors.toList());
    }

    //CREATE A NEW SALES ORDER
    public SalesOrder createSalesOrder(SalesOrderDTO dto) {
        //FIRST WE CONVERT THE DTO PRODUCTS TO ORDER-SAFE. AND VALIDATE ID
        List<OrderSafe> orderSafes = dto.getProducts().stream().map(item->
        {Product product = productRepository.findById(item.getProductId()).orElseThrow(
                () -> new ProductNotFoundExcpetion("Invalid product ID: " + item.getProductId()));

            OrderSafe os =  new OrderSafe();
            os.setProduct(product);
            os.setQuantityOrdered(item.getQuantityOrdered());
            return os;}).toList();
        //CREATE A SALES ORDER FROM DTO VALUES
        SalesOrder order = new SalesOrder(
                LocalDate.now(),
                OrderStatus.PENDING_PAYMENT,
                OrderType.SALES,
                orderSafes,
                dto.getCustomerName(),
                dto.getCustomerAddress()
        );
        //LINK AND SAVE
        orderSafes.forEach(os -> {os.setOrder(order);});
        return (SalesOrder) salesOrderRepository.save(order);
    }

    //SALES CHANGE STATUS TO PAYED -> DECREASE THE NUMBER OF STOCK OF THE PRODUCTS ORDER
    @Transactional
    public SalesOrder updateSalesStatus(Long orderId, OrderStatus newStatus) {
        SalesOrder order = salesOrderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundExcpetion("Order not found with ID " + orderId));

        if (order.getType() == OrderType.SALES
                && newStatus == OrderStatus.PAYED
                && order.getStatus() != OrderStatus.PAYED) {

            for (OrderSafe orderSafe : order.getOrderSafes()) {
                Product product = orderSafe.getProduct();
                Integer quantityOrdered = orderSafe.getQuantityOrdered();

                //UPDATE STOCK
                product.setStock(product.getStock() - quantityOrdered);
                productRepository.save(product);
            }
        }


        order.setStatus(newStatus);
        return salesOrderRepository.save(order);

    }

}
