package com.ironhack.products_inventory.service;

import com.ironhack.products_inventory.dto.SupplierDTO;
import com.ironhack.products_inventory.dto.UserDTO;
import com.ironhack.products_inventory.model.Role;
import com.ironhack.products_inventory.model.Supplier;
import com.ironhack.products_inventory.model.User;
import com.ironhack.products_inventory.repository.RoleRepository;
import com.ironhack.products_inventory.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
            if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    //CREATE NEW USER - SUPPLIER
    public SupplierDTO saveSupplierDTO(SupplierDTO dto) {
        // Check if username already exists
        if (userRepository.findByUsername(dto.getUsername()) != null) {
            throw new RuntimeException("Username '" + dto.getUsername() + "' is already taken.");
        }

        // Create and populate the Supplier entity
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setUsername(dto.getUsername());
        supplier.setPassword(passwordEncoder.encode(dto.getPassword()));
        supplier.setCompanyName(dto.getCompanyName());
        supplier.setCompanyAddress(dto.getCompanyAddress());

        // Save supplier
        Supplier savedSupplier = userRepository.save(supplier);

        // Convert to DTO
        return new SupplierDTO(
                savedSupplier.getUserId(),
                savedSupplier.getUsername(),
                savedSupplier.getName(),
                null, // don't expose password
                savedSupplier.getCompanyName(),
                savedSupplier.getCompanyAddress()
        );
    }

    //FIND USER BY USERNAME
    public UserDTO getUser(String username) {
        log.info("Fetching user {}", username);

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }

        // Map User to UserDTO, hiding password for safety (set null or exclude if you prefer)
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toList()),
                null, // or user.getPassword() if you want to include it (not recommended!)
                user.getName()
        );
    }

    //GET ALL USERS - RETURN EVERYTHING LESS PASSWORD
    public List<UserDTO> getUsers() {
        log.info("Fetching all users");

        return userRepository.findAll().stream()
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setId(user.getUserId());
                    dto.setUsername(user.getUsername());
                    dto.setName(user.getName());
                    dto.setRoles(user.getRoles().stream()
                            .map(Role::getName)
                            .collect(Collectors.toList()));
                    dto.setPassword(null); // HIDE PASSWORD IN RESPONSE
                    return dto;
                })
                .collect(Collectors.toList());
    }

    //METHOD NOT USED
    public void saveUserIfNotExists(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            userRepository.save(user);
        }
    }
    //WE KEEP IT BECAUSE WE USE IT TO SET USERS ON THE DEMO
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
