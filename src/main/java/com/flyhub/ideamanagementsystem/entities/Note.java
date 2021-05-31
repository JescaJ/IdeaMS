package com.flyhub.ideamanagementsystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity				
@Table(name="idea_notes", schema = "idea_ms")
public class Note extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "idea_id_gen", allocationSize = 1,initialValue = 1)
	private int note_id;
	
	private String note_content;

	private Integer idea_id;
	private Integer global_user_id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idea_id", insertable=false, updatable=false)
	private Idea ideaNoteMapping;
	
//	
//	@ManyToOne
//	@JoinColumn(name="global_user_id", insertable=false, updatable=false)
//	private Idea userNoteMapping;
	
	
	//constructor

	protected Note() {
		
	}

	
	//getters and setters
	public int getNote_id() {
		return note_id;
	}

	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}

	public String getNote_content() {
		return note_content;
	}

	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}

	
	
public Integer getGlobal_user_id() {
		return global_user_id;
	}


	public void setGlobal_user_id(Integer global_user_id) {
		this.global_user_id = global_user_id;
	}


public Idea getIdeaNoteMapping() {
		return ideaNoteMapping;
	}


	public void setIdeaNoteMapping(Idea ideaNoteMapping) {
		this.ideaNoteMapping = ideaNoteMapping;
	}

	
//	public Idea getUserNoteMapping() {
//		return userNoteMapping;
//	}
//
//
//	public void setUserNoteMapping(Idea userNoteMapping) {
//		this.userNoteMapping = userNoteMapping;
//	}


	//	for purposes of saving the idea id together with the note
	public void setIdea_id(Integer idea_id) {
		this.idea_id = idea_id;
	}


	public Integer getIdea_id() {
		return idea_id;
	}
	
}

