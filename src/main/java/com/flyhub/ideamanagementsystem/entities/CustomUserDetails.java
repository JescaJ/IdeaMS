package com.flyhub.ideamanagementsystem.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private User user;

	public CustomUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getPrimary_email();
	}
	
	public int getGlobal_user_id() {
		return user.getGlobal_user_id();
	}
	
	public String getFirst_name() {
		return user.getFirst_name();
	}
	
	public String getLast_name() {
		return user.getLast_name();
	}
	
	public String getMiddle_name() {
		return user.getMiddle_name();
	}
	
	public String getPrimary_email() {
		return user.getPrimary_email();
	}
	public int getGender_id() {
		return user.getGender_id();
	}
	
	public int getPrefix_id() {
		return user.getPrefix_id();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getFullName() {
		return user.getFirst_name() + " " + user.getLast_name();
	}

	@Override
	public String toString() {
		return user.toString();
	}
	
	
}
