package com.flyhub.ideamanagementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.flyhub.ideamanagementsystem.entities.CustomUserDetails;
import com.flyhub.ideamanagementsystem.entities.User;
import com.flyhub.ideamanagementsystem.repositories.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
	
	// this service is to enable us load a user by username from the db table

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String primary_email) throws UsernameNotFoundException {
		User user = repo.findByEmail(primary_email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}

}
