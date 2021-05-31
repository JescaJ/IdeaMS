package com.flyhub.ideamanagementsystem.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity				//this is for the user table in my db
@Table(name="users", schema = "idea_ms")
@Data
@DynamicUpdate
public class User{
//	extends Auditable<Integer>
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "global_user_id_gen", allocationSize = 1,initialValue = 1)
	private int global_user_id;

	
	private String full_name;
	
	private String first_name;
	
	private String middle_name;
	
	private String last_name;
	
	private String primary_email;
	
	
	private String password;
	
	//so as to set the gender_id value
	private Integer gender_id;
	
	private Integer prefix_id;
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles", 
			schema = "idea_ms",
			joinColumns = @JoinColumn(name = "global_user_id"), 
			inverseJoinColumns = @JoinColumn(name = "serial_id"))
	private Set<Role> roles = new HashSet<>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="prefix_id", insertable=false, updatable=false)
	private Prefix userPrefixMapping;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="gender_id", insertable=false, updatable=false)
	private Gender userGenderMapping;
	
//	@JsonIgnore
//	@OneToMany(mappedBy="ideaUserMapping")
//	private List<Idea> ideaToUser;
//	
	
	public User() {

	}
	
	public int getGlobal_user_id() {
		return global_user_id;
	}



	public void setGlobal_user_id(int global_user_id) {
		this.global_user_id = global_user_id;
	}


	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPrimary_email() {
		return primary_email;
	}


	public void setPrimary_email(String primary_email) {
		this.primary_email = primary_email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	

	public Integer getPrefix_id() {
		return prefix_id;
	}

	public void setPrefix_id(Integer prefix_id) {
		this.prefix_id = prefix_id;
	}

	public Integer getGender_id() {
		return gender_id;
	}

	public void setGender_id(Integer gender_id) {
		this.gender_id = gender_id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}

	

	public Gender getUserGenderMapping() {
		return userGenderMapping;
	}

	public void setUserGenderMapping(Gender userGenderMapping) {
		this.userGenderMapping = userGenderMapping;
	}

	public Prefix getUserPrefixMapping() {
		return userPrefixMapping;
	}

	public void setUserPrefixMapping(Prefix userPrefixMapping) {
		this.userPrefixMapping = userPrefixMapping;
	}

	
	
	
	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}



//	public List<Idea> getIdeaToUser() {
//		return ideaToUser;
//	}
//
//	public void setIdeaToUser(List<Idea> ideaToUser) {
//		this.ideaToUser = ideaToUser;
//	}

	@Override
	public String toString() {
		return "User [global_user_id=" + global_user_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", primary_email=" + primary_email + ", password=" + password + ", roles=" + roles + ", gender_id=" + gender_id + "]";
	}


	

}
