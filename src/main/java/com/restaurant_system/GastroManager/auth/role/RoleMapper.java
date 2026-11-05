package com.restaurant_system.GastroManager.auth.role;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper
{

    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
    List<RoleDto> toDtoList(List<Role> role);
    void updateRoleFromMapper (RoleDto roleDto , @MappingTarget Role role);
}
