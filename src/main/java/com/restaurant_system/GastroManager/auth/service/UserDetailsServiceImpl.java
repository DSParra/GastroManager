package com.restaurant_system.GastroManager.auth.service;

import com.restaurant_system.GastroManager.auth.role.Role;
import com.restaurant_system.GastroManager.auth.user.User;
import com.restaurant_system.GastroManager.auth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no encontrado"));
        return new  org.springframework.security.core.userdetails.User(
                user.getUsername() , user.getPassword() , mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map( role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
