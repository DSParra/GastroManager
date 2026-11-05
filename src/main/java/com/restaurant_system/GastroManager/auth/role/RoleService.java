package com.restaurant_system.GastroManager.auth.role;

import com.restaurant_system.GastroManager.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RoleService implements  IRoleService{

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Transactional(readOnly = true)
    @Override
    public List<RoleDto> findAll() {
        return roleMapper.toDtoList(roleRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public RoleDto findById(Long id) {
        return roleMapper.toDto(this.findEntityById(id));
    }

    @Transactional(readOnly = true)

    public RoleDto findByName(String name) {
        return roleMapper.toDto(roleRepository.findByName(name).orElseThrow( () -> new ResourceNotFoundException("role not found")));
    }



    @Transactional(readOnly = true)
    public Role findEntityById(Long id) {
        return roleRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("role not found"));
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = roleMapper.toEntity(roleDto);
        System.out.println("***************Role service ***********");
        System.out.println("dto");
        System.out.println(roleDto);
        System.out.println("entity");
        System.out.println(role);
        return roleMapper.toDto(roleRepository.save(role));
    }

    @Override
    public RoleDto update(Long id, RoleDto roleDto) {
        Role role = this.findEntityById(id);
        roleMapper.updateRoleFromMapper(roleDto, role);
        return roleMapper.toDto(roleRepository.save(role));
    }

    @Override
    public Boolean exitsByName(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public void deleteById(Long id) {
        Role role = this.findEntityById(id);
        roleRepository.delete(role);
    }
}
