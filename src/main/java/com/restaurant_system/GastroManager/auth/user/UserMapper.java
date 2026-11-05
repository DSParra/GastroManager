package com.restaurant_system.GastroManager.auth.user;

import com.restaurant_system.GastroManager.auth.dto.UserRequestDto;
import com.restaurant_system.GastroManager.auth.role.Role;
import com.restaurant_system.GastroManager.auth.role.RoleRepository;
import com.restaurant_system.GastroManager.exception.ResourceNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    private RoleRepository roleRepository;

    abstract UserResponseDto toResponseDto (User user);
    abstract List<UserResponseDto> toResponseDtoList (List<User> user);


    @Mapping(source ="userRequestDto.roles" , target = "roles" , qualifiedByName = "mapRoleStringsToRolesEntity")
    abstract void updateUserFromMapper (UserRequestDto userRequestDto , @MappingTarget User user);

    @Mapping(target = "password" , ignore = true)
    @Mapping(target = "id" , ignore = true)
    //    Agregar un mapeo deacuerdo a otro metodo , un mapeo dentro de otro
    @Mapping(source ="userRequestDto.roles" , target = "roles" , qualifiedByName = "mapRoleStringsToRolesEntity")
    public abstract User registerDtoToUser(UserRequestDto userRequestDto);

    @Named("mapRoleStringsToRolesEntity")
    public Set<Role> mapRoleStringsToRolesEntity(Set<String> rolenames){

        if(rolenames == null || rolenames.isEmpty()){
            return roleRepository.findByName("ROLE_USER")
                    .map(Collections::singleton)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: 'ROLE_USER' no encontrado en la base de datos"));

        }

        return rolenames.stream().map(
                roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Error: rol "+ roleName + "no encontrado")
                        )
        ).collect(Collectors.toSet());

    }





}
