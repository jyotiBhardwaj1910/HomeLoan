package com.example.home.loan.service.Impl;


import com.example.home.loan.Entity.RoleEntity;
import com.example.home.loan.Repository.RoleRepository;
import com.example.home.loan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity createRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<RoleEntity> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<RoleEntity> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
