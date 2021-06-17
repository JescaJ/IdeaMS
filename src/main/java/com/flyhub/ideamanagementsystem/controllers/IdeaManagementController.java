package com.flyhub.ideamanagementsystem.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.entities.AuthRequest;
import com.flyhub.ideamanagementsystem.entities.CustomUserDetails;
import com.flyhub.ideamanagementsystem.entities.Gender;
import com.flyhub.ideamanagementsystem.entities.Prefix;
import com.flyhub.ideamanagementsystem.entities.Role;
import com.flyhub.ideamanagementsystem.entities.User;
import com.flyhub.ideamanagementsystem.response.JwtResponse;
import com.flyhub.ideamanagementsystem.response.MessageResponse;
import com.flyhub.ideamanagementsystem.services.UserService;
import com.flyhub.ideamanagementsystem.util.JWTUtil;

//@Controller 
@RestController
@EnableAutoConfiguration
public class IdeaManagementController {
	
	@Autowired
	private UserService service;
	
	@Autowired
    private JWTUtil jwtUtil;
	
    @Autowired
    private AuthenticationManager authenticationManager;
	
	@GetMapping("")
	public String viewHomePage() {
		return "final_web";
	}
	
	@PostMapping("/signin")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
        	Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getPrimary_email(), authRequest.getPassword())
            );
        	 String jwt = jwtUtil.generateToken(authRequest.getPrimary_email());
     		
     		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();		
     		List<String> roles = userDetails.getAuthorities().stream()
     				.map(item -> item.getAuthority())
     				.collect(Collectors.toList());
     		return ResponseEntity.ok(new JwtResponse(jwt, 
     				 userDetails.getGlobal_user_id(), 
     				 userDetails.getUsername(), 
     				 userDetails.getFullName(), 
     				 userDetails.getFirst_name(), 
     				 userDetails.getLast_name(), 
     				 userDetails.getGender_id(), 
     				 userDetails.getPrefix_id(), 
     				 roles));
        }
	
		  catch (Exception ex) { 
			  return ResponseEntity
						.badRequest()
						.body(new MessageResponse("invalid username/password"));
			  }
	}
	
	
	@RequestMapping("/user")
	public CustomUserDetails viewUser(@AuthenticationPrincipal CustomUserDetails customdetails) {
//		System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails() );
		return customdetails;
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
	public ResponseEntity<?> processRegistration(@RequestBody User user) {
		
		 service.saveUserWithDefaultRole(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully! Now proceed to login"));
	}
	

	@GetMapping("/list_users")
	public List<User> viewUsersList() {
		return service.listAll();
	}
	
	
	@GetMapping("/roles/edit/{global_user_id}")
	public String editUserRoles(@PathVariable("global_user_id") int global_user_id, Model model) {
		User user = service.get(global_user_id);
		List<Role> listRoles = service.getRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_roles_form";
	}
	
	
	@PutMapping("/roles/save/{roles}")
	public User saveUserRoles(@PathVariable("roles") String roles, @RequestBody User user) {
		return service.saveUserUpdatedRoles(roles, user);
	}
	
	@RequestMapping("/user/delete/{global_user_id}")
	public void deleteProduct(@PathVariable(name = "global_user_id") int global_user_id) {
		service.delete(global_user_id);
	    return ;      
	}
	
	
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
	
	@PutMapping("/profile/save/{global_user_id}")
	public User saveUserProfile(@PathVariable("global_user_id") int global_user_id, @RequestBody User user) {
		return service.saveUserUpdatedProfile(global_user_id,user);
	}
}
