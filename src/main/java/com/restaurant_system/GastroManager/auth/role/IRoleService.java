package com.restaurant_system.GastroManager.auth.role;

import java.util.List;

public interface IRoleService {
List<RoleDto> findAll();
RoleDto findById(Long id);
Role findEntityById(Long id);
RoleDto save(RoleDto roleDto);
RoleDto update (Long id , RoleDto roleDto);
Boolean exitsByName(String name);
void deleteById(Long id);
}
