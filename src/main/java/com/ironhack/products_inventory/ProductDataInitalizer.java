package com.ironhack.products_inventory;

import com.ironhack.products_inventory.model.*;
import com.ironhack.products_inventory.repository.ProductRepository;
import com.ironhack.products_inventory.repository.PurchaseOrderRepository;
import com.ironhack.products_inventory.repository.SalesOrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static com.ironhack.products_inventory.enums.OrderStatus.PENDING_PAYMENT;
import static com.ironhack.products_inventory.enums.OrderType.PURCHASE;
import static com.ironhack.products_inventory.enums.OrderType.SALES;
import static com.ironhack.products_inventory.enums.OrderStatus.PAYED;

@Configuration
public class ProductDataInitalizer {

    @Bean
    CommandLineRunner productInitalizer(ProductRepository productsRepository, PurchaseOrderRepository purchaseOrderRepository, SalesOrderRepository salesOrderRepository) {        return args -> {

            // HELLO! HERE IT IS THE INITIAL PRODUCTS WE HAVE ON OUR WAREHOUSE:
            Product chair1 = new Product(null, "Standard Chair", "Comfortable wooden study chair", 25, 1, 50, null);
            Product chair2 = new Product(null, "Office Chair", "Standard office chair with wheels", 120, 2, 100, null);
            Product chair3 = new Product(null, "Gaming Chair", "Reclining gaming chair", 180, 1, 30, null);
            Product chair4 = new Product(null, "Dining Chair", "Set of 4 wooden chairs, perfect for living rooms", 200, 4, 20, null);
            Product chair5 = new Product(null, "Lounge Chair", "Modern fabric lounge chair", 220, 1, 25, null);

            productsRepository.saveAll(List.of(chair1, chair2, chair3, chair4, chair5));

            //WE CHOSE TWO PRODUCTS AVAILABLE TO SUPPLY
            OrderSafe op1 = new OrderSafe();
            op1.setProduct(chair2);
            op1.setQuantityOrdered(1);

            OrderSafe op2 = new OrderSafe();
            op2.setProduct(chair3);
            op2.setQuantityOrdered(5);

           //THAT'S AND ORDER OF PURCHASE
            PurchaseOrder porder1 = new PurchaseOrder(LocalDate.now(), PENDING_PAYMENT, PURCHASE, List.of(op1, op2), "IKAE");
            //WE ASSIGN EACH ORDERSAFE TO THE PURCHASE
            op1.setOrder(porder1);
            op2.setOrder(porder1);
            //SAVE THE PURCHASE
            purchaseOrderRepository.save(porder1);

            //SINGLE PRODUCT ORDER
            OrderSafe op3 = new OrderSafe();
            op3.setProduct(chair4);
            op3.setQuantityOrdered(5);
            PurchaseOrder porder2 = new PurchaseOrder(LocalDate.now(), PENDING_PAYMENT, PURCHASE, List.of(op3), "Carpinterie Ferrer" );
            op3.setOrder(porder2);
            purchaseOrderRepository.save(porder2);

            //WE HAVE A SALE OF 3 PRODUCTS
            OrderSafe os1 = new OrderSafe();
            os1.setProduct(chair5);
            os1.setQuantityOrdered(5);

            OrderSafe os2 = new OrderSafe();
            os2.setProduct(chair1);
            os2.setQuantityOrdered(1);

            OrderSafe os3 = new OrderSafe();
            os3.setProduct(chair2);
            os3.setQuantityOrdered(5);

            SalesOrder sorder1 = new SalesOrder(LocalDate.now(), PENDING_PAYMENT, SALES, List.of(os1, os2, os3), "Marco", "C.Major, 12");
            os1.setOrder(sorder1);
            os2.setOrder(sorder1);
            os3.setOrder(sorder1);

            salesOrderRepository.save(sorder1);

            System.out.println("🪑 Sample of Products, Purchase order and Sales Order inserted.");
        };

    }


}
