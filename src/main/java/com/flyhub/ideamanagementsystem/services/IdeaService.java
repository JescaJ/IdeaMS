package com.flyhub.ideamanagementsystem.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entities.Category;
import com.flyhub.ideamanagementsystem.entities.Idea;
import com.flyhub.ideamanagementsystem.repositories.CategoryRepository;
import com.flyhub.ideamanagementsystem.repositories.IdeaRepository;
import com.flyhub.ideamanagementsystem.repositories.UserRepository;

@Service
@Transactional
public class IdeaService {
	
	@Autowired
	private IdeaRepository ideaRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;


	 //return all ideas original
	public List<Idea> listAll() {
        return ideaRepo.findAll();
    }
	
	//second
//	 //return all ideas with pagination and searching
//	public Page<Idea> listAll(int pageNumber, String keyword) {
//		//searching
//		if (keyword != null) {
//			return ideaRepo.findAll(null, keyword);
//		}
//		//paging
//		Pageable pageable = PageRequest.of(pageNumber - 1, 3);
//        return ideaRepo.findAll(pageable);
//    }

	
     //save the idea for first time
//    public Object save(Idea idea, Authentication authentication) {
//    	
//    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//    	User user = userRepo.findByEmail(userDetails.getUsername());
//    	idea.setGlobal_user_id(user.getGlobal_user_id());
//    	System.out.println(user);
//    	return ideaRepo.save(idea);
//    }
	
	public Object save(Idea idea) {
    	return ideaRepo.save(idea);
    }
    
   //return single idea for edit original
//    public Idea get(int idea_id) {
//        return ideaRepo.findById(idea_id).get();
//    }
    
  //return single idea for edit
    public Idea get(int idea_id) {
        return ideaRepo.findById(idea_id).get();
    }
    
    //save updated idea
    public Idea saveUpdatedIdea(Idea idea, int idea_id) {
    	Idea currentIdea = ideaRepo.getOne(idea_id);
    	
    	currentIdea.setIdea_title(idea.getIdea_title());
    	currentIdea.setIdea_description(idea.getIdea_description());
    	
    	return ideaRepo.save(currentIdea);
	}
    
//    //update with DTO
//    public void updateIdea(IdeaDTO dto) {
//        Idea myIdea = ideaRepo.findById(dto.idea_id);
//        mapper.updateFromDto(dto, myIdea);
//        ideaRepo.save(myIdea);
//    }
    
     
    //delete idea
    public void delete(int idea_id) {
    	ideaRepo.deleteById(idea_id);
    }
	
  //for the lookup tables
  	public List<Category> getCategory(){
  		return categoryRepo.findAll();
  	}


}
