package com.flyhub.ideamanagementsystem.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity				//this is for the user table in my db
@Table(name="idea", schema = "idea_ms")
@DynamicUpdate
public class Idea extends Auditable<Integer>{
//	extends Auditable<Integer>
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "idea_id_gen", allocationSize = 1,initialValue = 1)
	private int idea_id;
	
	private String idea_title;
	
	private String idea_description;
	
	private String modified_reason;

	private Integer global_user_id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="category_id", insertable=false, updatable=false)
	private Category ideaCategoryMapping;
	private Integer category_id;
	
//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name="global_user_id", insertable=false, updatable=false) //probably the mapping should be created_by_global_user_id
//	private User ideaUserMapping;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="ideaNoteMapping")
	private List<Note> ideaToNote;
	
	//CONSTRUCTOR
	public Idea() {

	}
	
	//GETTERS AND SETTERS
	public int getIdea_id() {
		return idea_id;
	}


	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}

	
	
	public Integer getCategory_id() {
		return category_id;
	}


	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}


	public String getIdea_title() {
		return idea_title;
	}


	public void setIdea_title(String idea_title) {
		this.idea_title = idea_title;
	}


	public String getIdea_description() {
		return idea_description;
	}


	public void setIdea_description(String idea_description) {
		this.idea_description = idea_description;
	}
	
	
	
public Integer getGlobal_user_id() {
		return global_user_id;
	}


	public void setGlobal_user_id(Integer global_user_id) {
		this.global_user_id = global_user_id;
	}


	public Category getIdeaCategoryMapping() {
		return ideaCategoryMapping;
	}


	public void setIdeaCategoryMapping(Category ideaCategoryMapping) {
		this.ideaCategoryMapping = ideaCategoryMapping;
	}

		

//	public User getIdeaUserMapping() {
//		return ideaUserMapping;
//	}
//
//
//	public void setIdeaUserMapping(User ideaUserMapping) {
//		this.ideaUserMapping = ideaUserMapping;
//	}


	public List<Note> getIdeaToNote() {
		return ideaToNote;
	}


	public void setIdeaToNote(List<Note> ideaToNote) {
		this.ideaToNote = ideaToNote;
	}


	//	for purposes of saving the user id together with the idea
	public void setGlobal_user_id(int global_user_id) {
		this.global_user_id = global_user_id;
	}


	public String getModified_reason() {
		return modified_reason;
	}


	public void setModified_reason(String modified_reason) {
		this.modified_reason = modified_reason;
	}

	@Override
	public String toString() {
		return "Idea [idea_id=" + idea_id + ", idea_title=" + idea_title + ", idea_description=" + idea_description
				+ ", modified_reason=" + modified_reason + ", global_user_id=" + global_user_id
				+ ", ideaCategoryMapping=" + ideaCategoryMapping + ", category_id=" + category_id + ", ideaUserMapping="
				+ ", ideaToNote=" + ideaToNote + "]";
	}

	
	
}
