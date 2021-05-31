package com.flyhub.ideamanagementsystem.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "idea_category", schema = "idea_ms")
public class Category extends Auditable<Integer>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int category_id;
	
	private String category_name;

	private String category_description;
	
	@JsonIgnore
	@OneToMany(mappedBy="ideaCategoryMapping")
	private List<Idea> ideaToCategory;
	
		
	// CONSTRUCTOR
	public Category() {
	}
	
	// GETTERS AND SETTERS

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_description() {
		return category_description;
	}

	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}

	public List<Idea> getIdeaToCategory() {
		return ideaToCategory;
	}

	

	
}
