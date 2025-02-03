package com.example.home.loan.service;

import com.example.home.loan.Entity.RoleEntity;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    RoleEntity createRole(RoleEntity role);
    Optional<RoleEntity> getRoleById(Long id);
    Optional<RoleEntity> getRoleByName(String name);
    List<RoleEntity> getAllRoles();
    void deleteRole(Long id);
}
