package com.flyhub.ideamanagementsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entities.Gender;
import com.flyhub.ideamanagementsystem.repositories.GenderRepository;

@Service
public class GenderService {
	
	@Autowired
	private GenderRepository genderRepo;
	
	
	 //save the idea for first time
    public Gender save(Gender gender) {
    	
    	return genderRepo.save(gender);
    }

    //return all ideas original
	public List<Gender> listAll() {
        return genderRepo.findAll();
    }
    
    //return single idea for edit
    public Gender get(int gender_id) {
        return genderRepo.findById(gender_id).get();
    }
    
    //save updated idea
    public Object saveUpdatedGender(Gender gender,int gender_id) {
    	return genderRepo.save(gender);
	}
     
    //delete idea
    public void delete(int gender_id) {
    	genderRepo.deleteById(gender_id);
    }
 
}

