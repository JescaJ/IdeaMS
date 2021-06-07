package com.flyhub.ideamanagementsystem.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int global_user_id;
	private String fullname;
	private String primary_email;
	private List<String> roles;

	public JwtResponse(String accessToken, int global_user_id, String primary_email, String fullname, List<String> roles) {
		this.token = accessToken;
		this.global_user_id = global_user_id;
		this.primary_email = primary_email;
		this.fullname = fullname;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
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
