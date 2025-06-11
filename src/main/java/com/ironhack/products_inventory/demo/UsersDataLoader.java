package com.ironhack.products_inventory.demo;

import com.ironhack.products_inventory.model.Customer;
import com.ironhack.products_inventory.model.Role;
import com.ironhack.products_inventory.model.Supplier;
import com.ironhack.products_inventory.model.User;
import com.ironhack.products_inventory.repository.CustomerRepository;
import com.ironhack.products_inventory.repository.SupplierRepository;
import com.ironhack.products_inventory.service.RoleService;
import com.ironhack.products_inventory.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class UsersDataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;

    private Supplier supplier1;
    private Supplier supplier2;
    private Customer customer1;

    @Override
    public void run(String... args) throws Exception {

        //THERE ARE ONLY TWO ROLES
        roleService.save(new Role("ROLE_USER"));
        roleService.save(new Role("ROLE_ADMIN"));


        // CREATE USERS
        //CREATE CUSTOMERS
        Customer customer1 = new Customer("Marco", "marco", "1234", "Marco", "Carrer Major, 12, Lleida", 34);
        Customer customer2 = new Customer("Daniel", "dani", "1234", "Daniel", "Rambla Catalunya, 45, Barcelona", 29);
        Customer customer3 = new Customer("Lisa", "lisa", "1234","Lisa", "Avinguda Diagonal, 210, Barcelona", 31);
        Customer customer4 = new Customer("Kevin", "kevin", "1234", "Kevin", "Carrer de Mallorca, 330, Barcelona", 30);
        Customer customer5 = new Customer("Adriana", "adri", "1234", "Adriana", "Passeig de Gràcia, 15, Barcelona", 32);

        //CREATE SUPPLIER
        Supplier supplier1 = new Supplier("Marc", "marc", "1234","Marc el fuster", "Carrer de les Flors, 12, Vic");
        Supplier supplier2 = new Supplier("Matias", "mati", "1234", "Matias el planxista", "La Rambla Hospital, 13, Vic");
        Supplier supplier3 = new Supplier("Hebert", "hebert", "1234","Hebert el fuster", "Carrer Arquebisbe Alemany, 29, Vic");
        Supplier supplier4 = new Supplier("Carlos", "carlos", "1234","Carlos el llauner", "Plaça Gaudí, 2, Vic");
        Supplier supplier5 = new Supplier("Victor", "victor", "1234","Victor el fuster", "Carrer de la Riera, 15, Vic");


        // SAVE USERS
        userService.saveUser(customer1);
        userService.saveUser(customer2);
        userService.saveUser(customer3);
        userService.saveUser(customer4);
        userService.saveUser(customer5);
        userService.saveUser(supplier1);
        userService.saveUser(supplier2);
        userService.saveUser(supplier3);
        userService.saveUser(supplier4);
        userService.saveUser(supplier5);


        // ASSIGN ROLES
        roleService.addRoleToUser("lisa", "ROLE_USER");
        roleService.addRoleToUser("lisa", "ROLE_ADMIN");
        roleService.addRoleToUser("kevin", "ROLE_USER");
        roleService.addRoleToUser("dani", "ROLE_USER");
        roleService.addRoleToUser("adri", "ROLE_USER");
        roleService.addRoleToUser("marc", "ROLE_USER");
        roleService.addRoleToUser("marco", "ROLE_USER");
        roleService.addRoleToUser("marc", "ROLE_ADMIN");
        roleService.addRoleToUser("mati", "ROLE_USER");
        roleService.addRoleToUser("hebert", "ROLE_USER");
        roleService.addRoleToUser("carlos", "ROLE_USER");
        roleService.addRoleToUser("victor", "ROLE_USER");
    }
}
