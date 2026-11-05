package com.restaurant_system.GastroManager.auth.role;

import com.restaurant_system.GastroManager.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role , Long> {
    Optional<Role> findByName(String name);
    boolean existsByName(String name);

}
