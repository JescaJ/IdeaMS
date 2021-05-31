package com.flyhub.ideamanagementsystem.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.entities.Gender;
import com.flyhub.ideamanagementsystem.entities.Prefix;
import com.flyhub.ideamanagementsystem.entities.Role;
import com.flyhub.ideamanagementsystem.entities.User;
import com.flyhub.ideamanagementsystem.services.GenderService;
import com.flyhub.ideamanagementsystem.services.PrefixService;
import com.flyhub.ideamanagementsystem.services.UserService;

//@Controller 
@RestController
@EnableAutoConfiguration
public class IdeaManagementController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private GenderService genderService;
	
	@Autowired
	private PrefixService prefixService;
	
	
	@GetMapping("")
	public String viewHomePage() {
		return "final_web";
	}
	
	@GetMapping("/register")
	public String showSignupForm(Model model) {
		model.addAttribute("user", new User());
		
		
		List<Gender> genderList = service.getGender();
		model.addAttribute("genderList", genderList);
		
		List<Prefix> prefixList = service.getPrefix();
		model.addAttribute("prefixList", prefixList);
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		service.saveUserWithDefaultRole(user);
		return "registration_success";
	}
	
	//original
	@GetMapping("/list_users")
	@ResponseBody
	public List<User> viewUsersList() {
//		List<User> listUsers = service.listAll();
//		model.addAttribute("listUsers", listUsers);
//		
		return service.listAll();
	}
	
	//list ideas with search and pagination
//	@GetMapping("/list_ideas")
//	@RequestMapping(value = {"/list_users"}, method = RequestMethod.GET)
//	public String viewUsersList(Model model, @Param("keyword") String keyword) {
//		return viewUsersListPaged(model, 1, keyword);
//	}
//	
//	@GetMapping("/pages/{pageNumber}")
//	public String viewUsersListPaged(Model model, @PathVariable("pageNumber") int currentPage, String keyword) {
//		Page<User> page = service.listAll(currentPage, keyword);
//		long totalUsers = page.getTotalElements();
//		int totalPages = page.getTotalPages();
//		
//		List<User> listUsers = page.getContent();
//		
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("totalUsers", totalUsers);
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("listUsers", listUsers);
//		
//		return "list_users";
//	}
	
		
	@GetMapping("/roles/edit/{global_user_id}")
	public String editUserRoles(@PathVariable("global_user_id") int global_user_id, Model model) {
		User user = service.get(global_user_id);
		List<Role> listRoles = service.getRoles();
//		List<Gender> genderList = service.getGender();
//		model.addAttribute("genderList", genderList);
		
//		List<Prefix> prefixList = service.getPrefix();
//		model.addAttribute("prefixList", prefixList);
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_roles_form";
	}
	
	
	@PostMapping("/roles/save")
	public String saveUserRoles(User user) {
		service.saveUserUpdatedRoles(user);
		return "redirect:/list_users";
	}
	
	@RequestMapping("/user/delete/{global_user_id}")
	public String deleteProduct(@PathVariable(name = "global_user_id") int global_user_id) {
		service.delete(global_user_id);
	    return "redirect:/list_users";       
	}
	
//	@RequestMapping("/profile")
//	public String viewProfile(@AuthenticationPrincipal CustomUserDetails customdetails, Model model) {
////		System.out.println(customdetails); 
//		int gendid = customdetails.getGender_id();
//		int prefid = customdetails.getPrefix_id();
//		
//		Gender gender = genderService.get(gendid);
//		Prefix prefix = prefixService.get(prefid);
//		Integer global_id = customdetails.getGlobal_user_id();
//		
//		String fname = customdetails.getFirst_name();
//		String mname = customdetails.getMiddle_name();
//		String lname = customdetails.getLast_name();
//		String email = customdetails.getPrimary_email();
//		String genderValue = gender.getGender_name();
//		String prefixValue = prefix.getPrefix_name();
//		
//		
//		List<Gender> genderList = service.getGender();
//		List<Prefix> prefixList = service.getPrefix();
//		
//		model.addAttribute("genderList", genderList);
//		model.addAttribute("prefixList", prefixList);
////		
//////		List <Gender>  genderValues = service.getGender();
//		
//////		System.out.println(gender.getGender_name()); 
//		
//		model.addAttribute("fname", fname);
//		model.addAttribute("mname", mname);
//		model.addAttribute("lname", lname);
//		model.addAttribute("email", email);
//		model.addAttribute("global_id", global_id);
//		model.addAttribute("genderValue", genderValue);
//		model.addAttribute("prefixValue", prefixValue);
//		
//		
//		
//		return "profile";
//	}
	
	@RequestMapping("/profile/{global_user_id}")
	public String viewProfile(@PathVariable("global_user_id") int global_user_id, Model model) {
		User user = service.get(global_user_id);
		List<Gender> genderList = service.getGender();
		List<Prefix> prefixList = service.getPrefix();
		
		model.addAttribute("genderList", genderList);
		model.addAttribute("prefixList", prefixList);
		model.addAttribute("user", user);
		return "profile";
	}
	
	@PostMapping("/profile/save")
	public String saveUserProfile(User user) {
		service.saveUserUpdatedProfile(user);
		
		return "profile";
	}
}
