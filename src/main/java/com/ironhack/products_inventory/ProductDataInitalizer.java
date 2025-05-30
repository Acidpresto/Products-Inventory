package com.ironhack.products_inventory;

import com.ironhack.products_inventory.model.*;
import com.ironhack.products_inventory.repository.OrdersRepository;
import com.ironhack.products_inventory.repository.ProductRepository;
import com.ironhack.products_inventory.repository.PurchaseOrderRepository;
import com.ironhack.products_inventory.repository.SalesOrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static com.ironhack.products_inventory.enums.OrderOrigin.PURCHASE;
import static com.ironhack.products_inventory.enums.OrderOrigin.SALES;
import static com.ironhack.products_inventory.enums.OrderStatus.PAYED;
import static com.ironhack.products_inventory.enums.OrderStatus.PENDING_PAYMENT;

@Configuration
public class ProductDataInitalizer {

    @Bean
    CommandLineRunner productInitalizer(ProductRepository productsRepository, PurchaseOrderRepository purchaseOrderRepository, SalesOrderRepository salesOrderRepository) {        return args -> {

            // HELLO! HERE IT IS THE PRODUCTS WE HAVE ON OUR WAREHOUSE
            Products chair1 = new Products(null, "Standard Chair", "Comfortable chair with lumbar support", 250, 1, 50, null);
            Products chair2 = new Products(null, "Office Chair", "Standard office chair with wheels", 120, 2, 100, null);
            Products chair3 = new Products(null, "Gaming Chair", "Reclining gaming chair with headrest", 300, 1, 30, null);
            Products chair4 = new Products(null, "Dining Chair", "Set of 4 wooden dining chairs", 200, 4, 20, null);
            Products chair5 = new Products(null, "Lounge Chair", "Modern fabric lounge chair", 180, 1, 25, null);

            productsRepository.saveAll(List.of(chair1, chair2, chair3, chair4, chair5));

            //WE CHOSE TWO PRODUCTS AVAILABLE TO SUPPLY
            OrderSafe op1 = new OrderSafe();
            op1.setProduct(chair2);
            op1.setQuantityOrdered(1);

            OrderSafe op2 = new OrderSafe();
            op2.setProduct(chair3);
            op2.setQuantityOrdered(5);

           //THAT'S AND ORDER OF PURCHASE
            PurchaseOrder porder1 = new PurchaseOrder(LocalDate.now(), PAYED, PURCHASE, List.of(op1, op2), "IKAE");
            //WE ASSIGN EACH ORDERSAFE TO THE PURCHASE
            op1.setOrder(porder1);
            op2.setOrder(porder1);
            //SAVE THE PURCHASE
            purchaseOrderRepository.save(porder1);

            //SINGLE PRODUCT ORDER
            OrderSafe op3 = new OrderSafe();
            op3.setProduct(chair4);
            op3.setQuantityOrdered(5);
            PurchaseOrder porder2 = new PurchaseOrder(LocalDate.now(), PAYED, PURCHASE, List.of(op3), "Carpinterie Ferrer" );
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

            SalesOrder sorder1 = new SalesOrder(LocalDate.now(), PAYED, SALES, List.of(os1, os2, os3), "Marco", "C.Major, 12");
            os1.setOrder(sorder1);
            os2.setOrder(sorder1);
            os3.setOrder(sorder1);

            salesOrderRepository.save(sorder1);


            System.out.println("🪑 Sample of Products, Purchase order and Sales Order inserted.");
        };

    }


}
