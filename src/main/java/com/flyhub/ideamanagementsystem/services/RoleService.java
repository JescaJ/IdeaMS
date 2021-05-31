package com.flyhub.ideamanagementsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entities.Role;
import com.flyhub.ideamanagementsystem.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	 //save the role for first time
    public void save(Role role) {
    	
    	roleRepo.save(role);
    }

    //return all roles 
	public List<Role> listAll() {
        return roleRepo.findAll();
    }
    
    //return single idea for edit
    public Role get(int serial_id) {
        return roleRepo.findById(serial_id).get();
    }
    
    //save updated idea
    public void saveUpdatedRole(Role role) {
    	roleRepo.save(role);
	}
     
    //delete idea
    public void delete(int serial_id) {
    	roleRepo.deleteById(serial_id);
    }
}
