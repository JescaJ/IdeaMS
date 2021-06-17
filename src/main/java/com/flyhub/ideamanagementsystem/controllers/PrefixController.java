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

import com.flyhub.ideamanagementsystem.entities.Prefix;
import com.flyhub.ideamanagementsystem.services.PrefixService;

@RestController
@EnableAutoConfiguration
public class PrefixController {

	@Autowired
	private PrefixService prefixService;
	
	
	@GetMapping("/add_prefix")
	public String prefixAddForm(Model model) {
		model.addAttribute("prefix", new Prefix());
		return "prefix_form";
	}
	
	@PostMapping("/process_prefix")
	public Object processPrefix(@RequestBody Prefix prefix) {
		return prefixService.save(prefix);
	}
	
	@GetMapping("/list_prefix")
	public List<Prefix> viewPrefixList() {
		
		return prefixService.listAll();
	}
	
	@GetMapping("/prefix/edit/{prefix_id}")
	public String editPrefix(@PathVariable("prefix_id") int prefix_id, Model model) {
		Prefix prefix = prefixService.get(prefix_id);
		model.addAttribute("prefix", prefix);
		return "prefix_edit_form";
	}
	
	
	@PutMapping("/prefix/save/{prefix_id}")
	public Prefix savePrefixEdit(@PathVariable("prefix_id") int prefix_id, @RequestBody Prefix prefix) {
		
		return prefixService.saveUpdatedPrefix(prefix, prefix_id);
	}
	
	@RequestMapping("/prefix/delete/{prefix_id}")
	public void deletePrefix(@PathVariable(name = "prefix_id") int prefix_id) {
		prefixService.delete(prefix_id);
	    return;       
	}
}
