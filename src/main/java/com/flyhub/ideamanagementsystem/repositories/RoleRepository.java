package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flyhub.ideamanagementsystem.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	@Query("SELECT r FROM Role r WHERE r.role_name = ?1")
	public Role findByName(String role_name);
	
	@Query("SELECT r FROM Role r WHERE r.role_name = ?1")
	public Role findByRoleName(String role_name);
}
