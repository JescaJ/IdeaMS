package com.flyhub.ideamanagementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entities.CustomUserDetails;
import com.flyhub.ideamanagementsystem.entities.User;
import com.flyhub.ideamanagementsystem.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

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
