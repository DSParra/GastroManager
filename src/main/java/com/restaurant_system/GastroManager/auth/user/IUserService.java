package com.restaurant_system.GastroManager.auth.user;

import com.restaurant_system.GastroManager.auth.dto.UserRequestDto;
import com.restaurant_system.GastroManager.auth.role.Role;
import com.restaurant_system.GastroManager.auth.role.RoleDto;

import java.util.List;

public interface IUserService {
    List<UserResponseDto> findAll();
    UserResponseDto findById(Long id);
    User findEntityById(Long id);
    UserResponseDto save(UserRequestDto userResponseDto);
    UserResponseDto update (Long id , UserRequestDto userResponseDto);
    Boolean exitsByName(String name);
    void deleteById(Long id);




}
