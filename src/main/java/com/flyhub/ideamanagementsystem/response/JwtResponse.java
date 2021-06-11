package com.flyhub.ideamanagementsystem.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int global_user_id;
	private int gender_id;
	private int prefix_id;
	private String fullname;
	private String first_name;
	private String last_name;
	private String primary_email;
	private List<String> roles;

//	public JwtResponse(String accessToken, int global_user_id, String primary_email, String fullname, String first_name, String last_name, List<String> roles) {
//		this.token = accessToken;
//		this.global_user_id = global_user_id;
//		this.primary_email = primary_email;
//		this.fullname = fullname;
//		this.first_name = first_name;
//		this.last_name = last_name;
//		this.roles = roles;
//	}

	

	public String getAccessToken() {
		return token;
	}

	public JwtResponse(String accessToken, int global_user_id, String primary_email,String fullname, String first_name, String last_name,
			int gender_id, int prefix_id,List<String> roles) {
	this.token = accessToken;
	this.global_user_id = global_user_id;
	this.fullname = fullname;
	this.first_name = first_name;
	this.last_name = last_name;
	this.primary_email = primary_email;
	this.gender_id = gender_id;
	this.prefix_id = prefix_id;
	this.roles = roles;
}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	
	

	public int getGender_id() {
		return gender_id;
	}

	public void setGender_id(int gender_id) {
		this.gender_id = gender_id;
	}

	public int getPrefix_id() {
		return prefix_id;
	}

	public void setPrefix_id(int prefix_id) {
		this.prefix_id = prefix_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getGlobal_user_id() {
		return global_user_id;
	}

	public void setGlobal_user_id(int global_user_id) {
		this.global_user_id = global_user_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPrimary_email() {
		return primary_email;
	}

	public void setPrimary_email(String primary_email) {
		this.primary_email = primary_email;
	}

	public List<String> getRoles() {
		return roles;
	}
}
