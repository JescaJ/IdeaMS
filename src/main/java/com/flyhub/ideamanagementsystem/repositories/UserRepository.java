package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flyhub.ideamanagementsystem.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT u FROM User u WHERE u.primary_email =?1")
	User  findByEmail(String primary_email);
	
	
	@Query("SELECT u FROM User u WHERE u.first_name LIKE %?1%" 
			+ "OR u.last_name LIKE %?1%")
			public Page<User> findAll(Pageable pageable, String keyword);
}
