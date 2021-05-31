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

import com.flyhub.ideamanagementsystem.entities.Role;
import com.flyhub.ideamanagementsystem.services.RoleService;

@Controller
@EnableAutoConfiguration
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	
	@GetMapping("/add_role")
	public String roleAddForm(Model model) {
		model.addAttribute("role", new Role());
		return "role_form";
	}
	
	
	@PostMapping("/process_role")
	public String processRole(Role role) {
		roleService.save(role);
		return "index";
	}
	
	@GetMapping("/list_role")
	public String viewRoleList(Model model) {
		List<Role> listRole = roleService.listAll();
		model.addAttribute("listRole", listRole);
		
		return "list_role";
	}
	
	@GetMapping("/role/edit/{serial_id}")
	public String editUserRoles(@PathVariable("serial_id") int serial_id, Model model) {
		Role role = roleService.get(serial_id);
		model.addAttribute("role", role);
		return "role_edit_form";
	}
	
	
	@PostMapping("/role/save")
	public String saveRoleEdit(Role role) {
		roleService.saveUpdatedRole(role);
		return "redirect:/list_role";
	}
	
	@RequestMapping("/role/delete/{serial_id}")
	public String deleteRole(@PathVariable(name = "serial_id") int serial_id) {
		roleService.delete(serial_id);
	    return "redirect:/list_role";       
	}
}
