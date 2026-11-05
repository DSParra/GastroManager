package com.restaurant_system.GastroManager.auth.service;

import com.restaurant_system.GastroManager.auth.dto.JwtAuthResponseDto;
import com.restaurant_system.GastroManager.auth.dto.LoginDto;
import com.restaurant_system.GastroManager.auth.dto.UserRequestDto;
import com.restaurant_system.GastroManager.auth.user.User;
import com.restaurant_system.GastroManager.auth.user.UserMapper;
import com.restaurant_system.GastroManager.auth.user.UserRepository;
import com.restaurant_system.GastroManager.security.jwt.JwtGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;

@Service
@AllArgsConstructor
public class AuthService  implements  IAuthService{

    private AuthenticationManager authenticationManager;
    private JwtGenerator jwtGenerator;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtAuthResponseDto logIn(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername() , loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);


        return new JwtAuthResponseDto(token);



    }

    @Override
    public String registerUser(UserRequestDto userRequestDto) {
        if(userRepository.existsByUsername(userRequestDto.getUsername())){
            throw new KeyAlreadyExistsException("Usuario ya existente");
        }
        if(userRepository.existsByEmail(userRequestDto.getEmail())){
            throw new KeyAlreadyExistsException("Correo  ya registrado");
        }

        User user =  userMapper.registerDtoToUser(userRequestDto);

        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        userRepository.save(user);

        return "Usuario registrado";

    }
}
