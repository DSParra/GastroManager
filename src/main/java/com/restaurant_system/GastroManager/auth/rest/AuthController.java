package com.restaurant_system.GastroManager.auth.rest;

import com.restaurant_system.GastroManager.auth.dto.JwtAuthResponseDto;
import com.restaurant_system.GastroManager.auth.dto.LoginDto;
import com.restaurant_system.GastroManager.auth.dto.UserRequestDto;
import com.restaurant_system.GastroManager.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> logIn (@Valid @RequestBody LoginDto loginDto){
        JwtAuthResponseDto jwtAuthResponseDto = authService.logIn(loginDto);
        return ResponseEntity.ok(jwtAuthResponseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDto userRequestDto){
        System.out.println("Entrrando controller registrer");
        String response = authService.registerUser(userRequestDto);
        return new ResponseEntity<>( response ,HttpStatus.CREATED);

    }
}
