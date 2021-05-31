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

import com.flyhub.ideamanagementsystem.entities.Category;
import com.flyhub.ideamanagementsystem.services.CategoryService;

@RestController
@EnableAutoConfiguration
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/add_category")
	public String prefixAddForm(Model model) {
		model.addAttribute("category", new Category());
		return "category_form";
	}
	
	
	@PostMapping("/process_category")
	public String processCategory(Category category) {
		categoryService.save(category);
		return "index";
	}
	
//	@GetMapping("/list_category")
//	public String viewCategoryList(Model model) {
//		List<Category> listCategory = categoryService.listAll();
//		model.addAttribute("listCategory", listCategory);
//		
//		return "list_category";
//	}
	
	@GetMapping("/list_category")
	public List<Category> viewCategoryList() {		
		return categoryService.listAll();
	}
	
	@GetMapping("/category/edit/{category_id}")
	public String editCategory(@PathVariable("category_id") int category_id, Model model) {
		Category category = categoryService.get(category_id);
		model.addAttribute("category", category);
		return "category_edit_form";
	}
	
	
	@PostMapping("/category/save")
	public String saveCategoryEdit(Category category) {
		categoryService.saveUpdatedCategory(category);
		return "redirect:/list_category";
	}
	
	@RequestMapping("/category/delete/{category_id}")
	public String deleteCategory(@PathVariable(name = "category_id") int category_id) {
		categoryService.delete(category_id);
	    return "redirect:/list_category";       
	}
}
