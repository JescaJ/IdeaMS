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
	
	
//	@AuthenticationPrincipal CustomUserDetails customdetails,
//	@PostMapping("/process_add_idea")
//	public String processSubmission(Idea idea, Authentication authentication) {
//		
//		ideaService.save(idea,authentication);
//		return "submission_success";
//	}
	
	//for posting with vue js
	@PostMapping("/process_add_idea")
	public Object processSubmission(@RequestBody Idea idea) {
		return ideaService.save(idea);
	}
	
	
//	//original list ideas with vue js
	@GetMapping("/list_ideas")
	public ResponseEntity<List<Idea>> viewIdeasList() {
//		List<Idea> listIdeas = ideaService.listAll();
//		model.addAttribute("listIdeas", listIdeas);
		List<Idea> idea = ideaService.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(idea);
	}
	
	//list ideas with search and pagination
//	@GetMapping("/list_ideas")
//	@RequestMapping(value = {"/list_ideas", "/"}, method = RequestMethod.GET)
//	public String viewIdeasList(Model model, @Param("keyword") String keyword) {
//		return viewIdeasListPaged(model, 1, keyword);
//	}
//	
//	@GetMapping("/page/{pageNumber}")
//	public String viewIdeasListPaged(Model model, @PathVariable("pageNumber") int currentPage, String keyword) {
//		Page<Idea> page = ideaService.listAll(currentPage, keyword);
//	 
//		long totalIdeas = page.getTotalElements();
//		int totalPages = page.getTotalPages();
//		
//		List<Idea> listIdeas = page.getContent();
//		
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("totalIdeas", totalIdeas);
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("listIdeas", listIdeas);
//		
//		return "list_ideas";
//	}
	
	
	@GetMapping("/idea/edit/{idea_id}")
	public String editIdea(@PathVariable("idea_id") int idea_id, Model model) {
		Idea idea = ideaService.get(idea_id);
//		System.out.println(idea);
//		idea.setIdea_title(idea.getIdea_title());
		
//		model.addAttribute("note", new Note());
		model.addAttribute("idea", idea);
		return "idea_edit_form";
	}
	
	
//	@PostMapping("/ideas/save")
//	public String saveIdeaEdit(Idea idea) { //added the note object so as to save the note as well
////		noteService.save(note);
//		ideaService.saveUpdatedIdea(idea);
//		return "redirect:/list_ideas";
//	}
	
//	@PutMapping("/ideas/save/{idea_id}")
//	public String saveIdeaEdit(@PathVariable("idea_id") int idea_id, @RequestBody Idea idea) { 
////		noteService.save(note);
//		
//		return "ideaService.saveUpdatedIdea(idea, idea_id)";
//	}
	
	
	// save the updated idea with vue js
	@PutMapping("/ideas/save/{idea_id}")
	public Idea update(@PathVariable("idea_id") int idea_id, @RequestBody Idea idea) {
		return ideaService.saveUpdatedIdea(idea, idea_id);
	}
	
//	@RequestMapping("/view/idea/{idea_id}")
//	public String viewIdea(@PathVariable(name = "idea_id") int idea_id, Model model) {
//		
//		Idea idea = ideaService.get(idea_id);
//		System.out.println(idea.toString());
//		
//		model.addAttribute("note", new Note());
//		model.addAttribute("idea", idea);
//	    return "view_idea";       
//	}
	
	
	// return a single idea with vue js
	@GetMapping("/view/idea/{idea_id}")
	public Idea viewIdea(@PathVariable(name = "idea_id") int idea_id) {
		return ideaService.get(idea_id);       
	}
	
//	original
//	@RequestMapping("/idea/delete/{idea_id}")
//	public String deleteIdea(@PathVariable(name = "idea_id") int idea_id) {
//		ideaService.delete(idea_id);
//	    return "redirect:/list_ideas";       
//	}
	
	
//	delete with vue js
	@RequestMapping("/idea/delete/{idea_id}")
	public void deleteIdea(@PathVariable(name = "idea_id") int idea_id) {
	    ideaService.delete(idea_id);
		return;       
	}
	
	
	
	@PostMapping("/add/comment")
	public Object addComment(@RequestBody Note note) { //added the note object so as to save the note as well
//		note.setIdea_id(idea.getIdea_id());
//		note.setGlobal_user_id(customdetails.getGlobal_user_id());
//		ideaNoteService.save(idea, note);
//		noteService.save(note, authentication);
//		ideaService.saveUpdatedIdea(idea);
//		System.out.println(idea);
		System.out.println(note.toString());
		return noteService.save(note);
	}
	
	@GetMapping("/list_comments")
	public List<Note> viewCommentsList() {
		return ideaService.listComments();
	}
}
