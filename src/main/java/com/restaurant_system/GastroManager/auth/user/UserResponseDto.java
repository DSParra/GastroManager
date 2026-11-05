package com.restaurant_system.GastroManager.auth.user;

import com.restaurant_system.GastroManager.auth.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;


}
