package com.flyhub.ideamanagementsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entities.Prefix;
import com.flyhub.ideamanagementsystem.repositories.PrefixRepository;

@Service
public class PrefixService {
	
	@Autowired
	private PrefixRepository prefixRepo;
	
	
	 //save the idea for first time
    public void save(Prefix prefix) {
    	
    	prefixRepo.save(prefix);
    }

    //return all ideas original
	public List<Prefix> listAll() {
        return prefixRepo.findAll();
    }
    
    //return single idea for edit
    public Prefix get(int prefix_id) {
        return prefixRepo.findById(prefix_id).get();
    }
    
    //save updated idea
    public void saveUpdatedPrefix(Prefix prefix) {
    	prefixRepo.save(prefix);
	}
     
    //delete idea
    public void delete(int prefix_id) {
    	prefixRepo.deleteById(prefix_id);
    }
}
