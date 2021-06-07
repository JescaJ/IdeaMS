package com.flyhub.ideamanagementsystem.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flyhub.ideamanagementsystem.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	//this is implemented so as to access the Jpa 
	//crud repository methods on our entity created
	
	// we need to define a method that enables searching the user by username
	@Query("SELECT u FROM User u WHERE u.primary_email =?1")
	User  findByEmail(String primary_email);
	
	
	//for serach and pagination
	@Query("SELECT u FROM User u WHERE u.first_name LIKE %?1%" 
			+ "OR u.last_name LIKE %?1%")
			public Page<User> findAll(Pageable pageable, String keyword);
}
