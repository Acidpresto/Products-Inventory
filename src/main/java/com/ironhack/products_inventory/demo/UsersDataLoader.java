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

        // todo 1 poner esta info en los customer y supplier
        // CREATE USERS
        //WILL BE CUSTOMERS
//        User lisa = new User("Lisa", "lisa", "1234");
//        User kevin = new User("Kevin", "kevin", "1234");
//        User daniel = new User("Daniel", "dani", "1234");
//        User adriana = new User("Adriana", "adri", "1234");
//        User marco = new User("Marco", "marco", "1234");
//        //WILL BE SUPPLIERS
//        User marc = new User("Marc", "marc", "1234");
//        User matias = new User("Matias", "matias", "1234");
//        User hebert = new User("Hebert", "hebert", "1234");
//        User carlos = new User("Carlos", "carlos", "1234");
//        User victor = new User("Victor", "victor", "1234");
//
//
//        //CREATE CUSTOMERS
//        Customer customer1 = new Customer("Marco", "Carrer Major, 12, Lleida", 34, marco);
//        Customer customer2 = new Customer("Daniel", "Rambla Catalunya, 45, Barcelona", 29, daniel);
        Customer customer3 = new Customer("Lisa", "lisa", "1234","Lisa", "Avinguda Diagonal, 210, Barcelona", 31);
//        Customer customer4 = new Customer("Kevin", "Carrer de Mallorca, 330, Barcelona", 36, kevin);
//        Customer customer5 = new Customer("Adriana", "Passeig de Gràcia, 15, Barcelona", 40, adriana);
//
//        //CREATE SUPPLIER
//
//        Supplier supplier1 = new Supplier("Marc el fuster", "Carrer de les Flors, 12, Vic", marc);
//        Supplier supplier2 = new Supplier("Matias el planxista", "La Rambla Hospital, 13, Vic", matias);
//        Supplier supplier3 = new Supplier("Hebert el fuster", "Carrer Arquebisbe Alemany, 29, Vic", hebert);
//        Supplier supplier4 = new Supplier("Carlos el llauner", "Plaça Gaudí, 2, Vic", carlos);
//        Supplier supplier5 = new Supplier("Victor el fuster", "Carrer de la Riera, 15, Vic", victor);


        // SAVE USERS
        userService.saveUser(customer3);
//        userService.saveUser(kevin);
//        userService.saveUser(daniel);
//        userService.saveUser(adriana);
//        userService.saveUser(marco);
//        userService.saveUser(matias);
//        userService.saveUser(hebert);
//        userService.saveUser(carlos);
//        userService.saveUser(victor);

        // ASSIGN ROLES
        roleService.addRoleToUser("lisa", "ROLE_USER");
        roleService.addRoleToUser("lisa", "ROLE_ADMIN");
//        roleService.addRoleToUser("kevin", "ROLE_USER");
//        roleService.addRoleToUser("dani", "ROLE_USER");
//        roleService.addRoleToUser("adri", "ROLE_USER");
//        roleService.addRoleToUser("marc", "ROLE_USER");
//        roleService.addRoleToUser("marco", "ROLE_USER");
//        roleService.addRoleToUser("marc", "ROLE_ADMIN");
//        roleService.addRoleToUser("matias", "ROLE_USER");
//        roleService.addRoleToUser("hebert", "ROLE_USER");
//        roleService.addRoleToUser("carlos", "ROLE_USER");
//        roleService.addRoleToUser("victor", "ROLE_USER");


    }
}
