package com.restaurant_system.GastroManager.data;

import com.restaurant_system.GastroManager.auth.role.*;
import com.restaurant_system.GastroManager.auth.user.User;
import com.restaurant_system.GastroManager.auth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
@Component
@RequiredArgsConstructor
@Transactional
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("ROLE_ADMIN");
                    return roleRepository.save(newRole);
                });

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("ROLE_USER");
                    return roleRepository.save(newRole);
                });

        // --- 2. Crear un usuario ADMIN si no existe ---
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setName("Admin User");
            admin.setUsername("ADMIN");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setRoles(new HashSet<>(Collections.singletonList(adminRole)));
            userRepository.save(admin);
        }

        if (!userRepository.existsByUsername("user")) {
            User admin = new User();
            admin.setName("user");
            admin.setUsername("user");
            admin.setEmail("email@gmail.com");
            admin.setPassword(passwordEncoder.encode("pass123"));
            admin.setRoles(new HashSet<>(Collections.singletonList(adminRole)));
            userRepository.save(admin);
        }


        // --- 1. Crear y Guardar Roles si no existen ---
//
//        RoleDto roleAdminDto;
//        String adminName = "ROLE_ADMIN";
//        if( roleService.exitsByName(adminName)){
//            roleAdminDto = roleService.findByName(adminName);
//        }else{
//            RoleDto roleAdmin = new RoleDto(adminName);
//            System.out.println("---------------------------ROLE ADMIN----------------------------");
//            System.out.println(roleAdmin);
//            roleAdminDto = roleService.save(roleAdmin);
//        }
//
//        String userName = "ROLE_USER";
//        RoleDto userRoleDto;
//        if( roleService.exitsByName(userName)){
//            roleService.findByName(userName);
//        }else{
//            RoleDto roleUser = new RoleDto(userName);
//            roleService.save(roleUser);
//        }
//
//        if (!userRepository.existsByUsername("ADMIN")) {
//            User admin = new User();
//            admin.setName("Admin User");
//            admin.setUsername("ADMIN");
//            admin.setEmail("admin@example.com");
//            admin.setPassword(passwordEncoder.encode("admin1234"));
//            admin.setRoles(new HashSet<>(Collections.singletonList(roleMapper.toEntity(roleAdminDto))));
//            userRepository.save(admin);
//        }
//
//
//
//
//



    }
}
