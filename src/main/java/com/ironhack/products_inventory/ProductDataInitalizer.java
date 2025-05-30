package com.ironhack.products_inventory;

import com.ironhack.products_inventory.model.Orders;
import com.ironhack.products_inventory.model.Products;
import com.ironhack.products_inventory.model.PurchaseOrder;
import com.ironhack.products_inventory.model.SalesOrder;
import com.ironhack.products_inventory.repository.OrdersRepository;
import com.ironhack.products_inventory.repository.ProductRepository;
import com.ironhack.products_inventory.repository.PurchaseOrderRepository;
import com.ironhack.products_inventory.repository.SalesOrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

import static com.ironhack.products_inventory.enums.OrderOrigin.PURCHASE;
import static com.ironhack.products_inventory.enums.OrderOrigin.SALES;
import static com.ironhack.products_inventory.enums.OrderStatus.PAYED;
import static com.ironhack.products_inventory.enums.OrderStatus.PENDING_PAYMENT;

@Configuration
public class ProductDataInitalizer {

    @Bean
    CommandLineRunner productInitalizer(ProductRepository productsRepository, PurchaseOrderRepository purchaseOrderRepository, SalesOrderRepository salesOrderRepository) {        return args -> {


            Products chair1 = new Products(null, "Standard Chair", "Comfortable chair with lumbar support", 250, 1, 50, null);
            Products chair2 = new Products(null, "Office Chair", "Standard office chair with wheels", 120, 2, 100, null);
            Products chair3 = new Products(null, "Gaming Chair", "Reclining gaming chair with headrest", 300, 1, 30, null);
            Products chair4 = new Products(null, "Dining Chair", "Set of 4 wooden dining chairs", 200, 4, 20, null);
            Products chair5 = new Products(null, "Lounge Chair", "Modern fabric lounge chair", 180, 1, 25, null);

            productsRepository.save(chair1);
            productsRepository.save(chair2);
            productsRepository.save(chair3);
            productsRepository.save(chair4);
            productsRepository.save(chair5);

            PurchaseOrder porder1 = new PurchaseOrder(LocalDate.now(), PAYED,2, chair3, "Carpentery Ferrer", PURCHASE) ;
            PurchaseOrder porder2 = new PurchaseOrder(LocalDate.now().minusDays(2), PAYED,1, chair4, "IKAE", PURCHASE);

            purchaseOrderRepository.save(porder1);
            purchaseOrderRepository.save(porder2);

            SalesOrder sorder1 = new SalesOrder(LocalDate.now(),PAYED, 5,  chair5, "Marco", "C.Major 25", SALES);
            SalesOrder sorder2 = new SalesOrder(LocalDate.now(), PENDING_PAYMENT, 5, chair1, "Lisa", "C.de la font 13", SALES);

            salesOrderRepository.save(sorder1);
            salesOrderRepository.save(sorder2);

            System.out.println("🪑 Sample of Products, Purchase order and Sales Order inserted.");
        };

    }


}
