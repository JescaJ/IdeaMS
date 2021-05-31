package com.flyhub.ideamanagementsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entities.Category;
import com.flyhub.ideamanagementsystem.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	 //save the idea for first time
    public void save(Category category) {
    	
    	categoryRepo.save(category);
    }

    //return all ideas original
	public List<Category> listAll() {
        return categoryRepo.findAll();
    }
    
    //return single idea for edit
    public Category get(int category_id) {
        return categoryRepo.findById(category_id).get();
    }
    
    //save updated idea
    public void saveUpdatedCategory(Category category) {
    	categoryRepo.save(category);
	}
     
    //delete idea
    public void delete(int category_id) {
    	categoryRepo.deleteById(category_id);
    }
}
