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
@Table(name = "gender", schema = "idea_ms")
public class Gender {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gender_id;
	
	private String gender_name;

	private String gender_description;
		
	@JsonIgnore
	@OneToMany(mappedBy="userGenderMapping")
	private List<User> genderToUser;
	
	// CONSTRUCTOR
	public Gender() {
	}


	// GETTERS AND SETTERS
	public int getGender_id() {
		return gender_id;
	}


	public void setGender_id(int gender_id) {
		this.gender_id = gender_id;
	}


	public String getGender_name() {
		return gender_name;
	}


	public void setGender_name(String gender_name) {
		this.gender_name = gender_name;
	}


	public String getGender_description() {
		return gender_description;
	}


	public void setGender_description(String gender_description) {
		this.gender_description = gender_description;
	}


	public List<User> getGenderToUser() {
		return genderToUser;
	}


	public void setGenderToUser(List<User> genderToUser) {
		this.genderToUser = genderToUser;
	}

	
	
}
