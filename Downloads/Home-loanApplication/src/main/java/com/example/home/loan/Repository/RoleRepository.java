package com.example.home.loan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.home.loan.Entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	
	//RoleEntity findByName(String name);
	

    Optional<RoleEntity> findByName(String name);
}