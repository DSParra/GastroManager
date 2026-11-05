package com.restaurant_system.GastroManager.auth.dto;


import java.util.List;

public class UserResponseDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private List<RoleDto> roles;
}
