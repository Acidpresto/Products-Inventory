package com.ironhack.products_inventory.service;


import com.ironhack.products_inventory.model.Role;
import com.ironhack.products_inventory.model.User;
import com.ironhack.products_inventory.repository.RoleRepository;
import com.ironhack.products_inventory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Role save(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }


    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);

        User user = userRepository.findByUsername(username);
        // IF WE WANT THAT ONE USER HAVE A UNIQUE ROLE --- (THAT'S NOT OUR CASE, LISA & MARC ARE USERS AND ADMIN)
        // if (user != null) {
        // throw new RuntimeException("User with name " + username + " already exists");
        //        }

        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RuntimeException("Role with name " + roleName + " does not exist");
        }

        List<Role> userRoles = user.getRoles();
        userRoles.add(role);

        userRepository.save(user);
    }

}


