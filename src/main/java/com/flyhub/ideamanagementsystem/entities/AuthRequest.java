package com.flyhub.ideamanagementsystem.entities;

public class AuthRequest {
	
	private String primary_email;
	
	private String password;

	protected AuthRequest() {
	}

	protected AuthRequest(String primary_email, String password) {
		this.primary_email = primary_email;
		this.password = password;
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
	
	
	
}
