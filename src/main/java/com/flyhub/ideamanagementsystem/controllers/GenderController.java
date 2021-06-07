package com.flyhub.ideamanagementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.entities.Gender;
import com.flyhub.ideamanagementsystem.services.GenderService;

@RestController
@EnableAutoConfiguration
public class GenderController {

	@Autowired
	private GenderService genderService;
	
	
	@GetMapping("/add_gender")
	public String prefixAddForm(Model model) {
		model.addAttribute("gender", new Gender());
		return "gender_form";
	}
	
	
	@PostMapping("/process_gender")
	public Gender processGender(@RequestBody Gender gender) {
		
		return genderService.save(gender);
	}
	
//	@GetMapping("/list_gender")
//	public String viewGenderList(Model model) {
//		List<Gender> listGender = genderService.listAll();
//		model.addAttribute("listGender", listGender);
//		
//		return "list_gender";
//	}
	
	@GetMapping("/list_gender")
	public List<Gender> viewGenderList() {
		
		return genderService.listAll();
	}
	
	@GetMapping("/gender/edit/{gender_id}")
	public String editUserRoles(@PathVariable("gender_id") int gender_id, Model model) {
		Gender gender = genderService.get(gender_id);
		model.addAttribute("gender", gender);
		return "gender_edit_form";
	}
	
	
	@PutMapping("/gender/save/{gender_id}")
	public Object saveIdeaEdit(@PathVariable("gender_id") int gender_id, @RequestBody Gender gender) {
		
		return genderService.saveUpdatedGender(gender, gender_id);
	}
	
	@RequestMapping("/gender/delete/{gender_id}")
	public void deleteIdea(@PathVariable(name = "gender_id") int gender_id) {
		
	    genderService.delete(gender_id);      
	}
}
