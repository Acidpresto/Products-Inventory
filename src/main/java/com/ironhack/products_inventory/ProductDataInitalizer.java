package com.ironhack.products_inventory;

import com.ironhack.products_inventory.model.Products;
import com.ironhack.products_inventory.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDataInitalizer {

    @Bean
    CommandLineRunner productInitalizer(ProductRepository productsRepository) {
        return args -> {
            Products chair1 = new Products(null, "Standard Chair", "Comfortable chair with lumbar support", 250, 1, 50);
            Products chair2 = new Products(null, "Office Chair", "Standard office chair with wheels", 120, 2, 100);
            Products chair3 = new Products(null, "Gaming Chair", "Reclining gaming chair with headrest", 300, 1, 30);
            Products chair4 = new Products(null, "Dining Chair", "Set of 4 wooden dining chairs", 200, 4, 20);
            Products chair5 = new Products(null, "Lounge Chair", "Modern fabric lounge chair", 180, 1, 25);

            productsRepository.save(chair1);
            productsRepository.save(chair2);
            productsRepository.save(chair3);
            productsRepository.save(chair4);
            productsRepository.save(chair5);

            System.out.println("🪑 Sample chairs inserted into the database.");
        };

    }


}
