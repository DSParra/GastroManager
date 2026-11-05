package com.restaurant_system.GastroManager.auth.service;

import com.restaurant_system.GastroManager.auth.dto.JwtAuthResponseDto;
import com.restaurant_system.GastroManager.auth.dto.LoginDto;
import com.restaurant_system.GastroManager.auth.dto.UserRequestDto;

public interface IAuthService {
    JwtAuthResponseDto logIn(LoginDto loginDto);
    String registerUser(UserRequestDto userRequestDto);

}
