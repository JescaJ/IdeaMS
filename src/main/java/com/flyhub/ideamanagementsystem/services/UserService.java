package com.flyhub.ideamanagementsystem.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entities.Gender;
import com.flyhub.ideamanagementsystem.entities.Idea;
import com.flyhub.ideamanagementsystem.entities.Prefix;
import com.flyhub.ideamanagementsystem.entities.Role;
import com.flyhub.ideamanagementsystem.entities.User;
import com.flyhub.ideamanagementsystem.repositories.GenderRepository;
import com.flyhub.ideamanagementsystem.repositories.PrefixRepository;
import com.flyhub.ideamanagementsystem.repositories.RoleRepository;
import com.flyhub.ideamanagementsystem.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;  // reference to the user repository linking to the user entity
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private GenderRepository genderRepo;
	
	@Autowired
	private PrefixRepository prefixRepo;
	
	public Object saveUserWithDefaultRole(User user) {
		//to store the encrypted password
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
//		
		Role roleUser = roleRepo.findByName("USER");
		user.addRole(roleUser);
		
		return userRepo.save(user);
	}
	
	public User saveUserUpdatedRoles(String roles, User user) {
		
		User currentUser = userRepo.getOne(user.getGlobal_user_id());
		Role roleaddUser = roleRepo.findByName(roles);
		
		currentUser.addRole(roleaddUser);
		
		return userRepo.save(currentUser);
	}
	
	public void delete(int global_user_id) {
		userRepo.deleteById(global_user_id);
    }
	
	//list users original
	public List<User> listAll(){
		return userRepo.findAll();
	}
	
	
	//return user for edit
	public User get(int global_user_id) {
		return userRepo.findById(global_user_id).get();
	}
	
	//save updated profile
	public User saveUserUpdatedProfile(int global_user_id, User user) {
		
		User currentUser = userRepo.getOne(global_user_id);
    	
    	currentUser.setFirst_name(user.getFirst_name());
    	currentUser.setLast_name(user.getLast_name());
    	currentUser.setPrimary_email(user.getPrimary_email());
		return userRepo.save(currentUser);
	}
	
	public List<Role> getRoles(){
		return roleRepo.findAll();
	}
	
	//for the lookup tables
	public List<Gender> getGender(){
		return genderRepo.findAll();
	}
	
	public List<Prefix> getPrefix(){
		return prefixRepo.findAll();
	}
}
