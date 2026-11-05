package com.restaurant_system.GastroManager.security.config;

import com.restaurant_system.GastroManager.security.jwt.JwtAuthenticationFilter;
import com.restaurant_system.GastroManager.security.jwt.JwtEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity(debug = true )
@RequiredArgsConstructor
public class SecurityConfig {


    private JwtEntryPoint jwtEntryPoint;






    // aplica todos los friltros de seguridad definidos
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter){
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //habilitando h2
                .headers(AbstractHttpConfigurer::disable)

                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint))


                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/error").permitAll()

                                .anyRequest().authenticated()
                        )


        ;



        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();

    }



}