package com.restaurant_system.GastroManager.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "username required")
    private String username;
    @NotBlank(message = "email required")
    @Email(message = "must be a valid email")
    private String email;
    @Size(min = 4 , message = "min password size must be more than 3")
    private String password;

    private Set<String> roles;


}
