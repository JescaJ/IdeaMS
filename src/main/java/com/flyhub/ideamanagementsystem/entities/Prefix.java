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
@Table(name = "prefix", schema = "idea_ms")
public class Prefix {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int prefix_id;
	
	private String prefix_name;

	private String prefix_description;
	
	@JsonIgnore
	@OneToMany(mappedBy="userPrefixMapping")
	private List<User> prefixToUser;
		
	// CONSTRUCTOR
	public Prefix() {
	}

	// GETTERS AND SETTERS
	public int getPrefix_id() {
		return prefix_id;
	}

	public void setPrefix_id(int prefix_id) {
		this.prefix_id = prefix_id;
	}

	public String getPrefix_name() {
		return prefix_name;
	}

	public void setPrefix_name(String prefix_name) {
		this.prefix_name = prefix_name;
	}

	public String getPrefix_description() {
		return prefix_description;
	}

	public void setPrefix_description(String prefix_description) {
		this.prefix_description = prefix_description;
	}

	public List<User> getPrefixToUser() {
		return prefixToUser;
	}

	public void setPrefixToUser(List<User> prefixToUser) {
		this.prefixToUser = prefixToUser;
	}

	
}
