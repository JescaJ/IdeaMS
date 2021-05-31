package com.flyhub.ideamanagementsystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles", schema="idea_ms")
public class Role{
//	 extends Auditable<Integer>
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serial_id;
	
	
	private String role_name;

	private String role_description;
	
	public Role() {
		
	}

	public Role(String role_name) {
		this.role_name = role_name;
	}
	
	public Role(int serial_id) {
		this.serial_id = serial_id;
	}
	

	
	public Role(int serial_id, String role_name, String role_description) {
		this.serial_id = serial_id;
		this.role_name = role_name;
		this.role_description = role_description;
	}


	public int getSerial_id() {
		return serial_id;
	}


	public void setSerial_id(int serial_id) {
		this.serial_id = serial_id;
	}


	public String getRole_name() {
		return role_name;
	}


	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	

	public String getRole_description() {
		return role_description;
	}

	public void setRole_description(String role_description) {
		this.role_description = role_description;
	}

	@Override
	public String toString() {
		return this.role_name;
	}
	
	
}
