package com.flyhub.ideamanagementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String processGender(Gender gender) {
		genderService.save(gender);
		return "index";
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
	
	
	@PostMapping("/gender/save")
	public String saveIdeaEdit(Gender gender) {
		genderService.saveUpdatedGender(gender);
		return "redirect:/list_gender";
	}
	
	@RequestMapping("/gender/delete/{gender_id}")
	public String deleteIdea(@PathVariable(name = "gender_id") int gender_id) {
		genderService.delete(gender_id);
	    return "redirect:/list_gender";       
	}
}
