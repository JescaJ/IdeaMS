package com.flyhub.ideamanagementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.entities.Category;
import com.flyhub.ideamanagementsystem.entities.Idea;
import com.flyhub.ideamanagementsystem.entities.Note;
import com.flyhub.ideamanagementsystem.services.IdeaNotesService;
import com.flyhub.ideamanagementsystem.services.IdeaService;
import com.flyhub.ideamanagementsystem.services.NoteService;
import com.flyhub.ideamanagementsystem.services.UserService;

//@Controller 
@RestController
@EnableAutoConfiguration
public class IdeaController {
	
	@Autowired
	IdeaService ideaService;
	
	@Autowired
	NoteService noteService;
	
	@Autowired
	IdeaNotesService ideaNoteService;
	
	@Autowired
	private UserService  userservice;
	

	
	@GetMapping("/add_idea")
	public String showSignupForm(Model model) {
		model.addAttribute("idea", new Idea());
		
		//for the select populate
		List<Category> categoryList = ideaService.getCategory();
		model.addAttribute("categoryList", categoryList);
		
		return "idea_form";
	}

	
	@PostMapping("/process_add_idea")
	public Object processSubmission(@RequestBody Idea idea) {
		return ideaService.save(idea);
	}
	

	@GetMapping("/list_ideas")
	public ResponseEntity<List<Idea>> viewIdeasList() {
		List<Idea> idea = ideaService.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(idea);
	}
	
	
	@GetMapping("/idea/edit/{idea_id}")
	public String editIdea(@PathVariable("idea_id") int idea_id, Model model) {
		Idea idea = ideaService.get(idea_id);
		model.addAttribute("idea", idea);
		return "idea_edit_form";
	}
	

	@PutMapping("/ideas/save/{idea_id}")
	public Idea update(@PathVariable("idea_id") int idea_id, @RequestBody Idea idea) {
		return ideaService.saveUpdatedIdea(idea, idea_id);
	}
	

	@GetMapping("/view/idea/{idea_id}")
	public Idea viewIdea(@PathVariable(name = "idea_id") int idea_id) {
		return ideaService.get(idea_id);       
	}
	
	

	@RequestMapping("/idea/delete/{idea_id}")
	public void deleteIdea(@PathVariable(name = "idea_id") int idea_id) {
	    ideaService.delete(idea_id);
		return;       
	}
	
	
	@PostMapping("/add/comment")
	public Object addComment(@RequestBody Note note) { 
		return noteService.save(note);
	}
	
	@GetMapping("/list_comments")
	public List<Note> viewCommentsList() {
		return ideaService.listComments();
	}
}
