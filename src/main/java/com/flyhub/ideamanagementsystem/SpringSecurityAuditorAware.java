package com.flyhub.ideamanagementsystem;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import com.flyhub.ideamanagementsystem.entities.AuthRequest;

import com.flyhub.ideamanagementsystem.entities.CustomUserDetails;

class SpringSecurityAuditorAware implements AuditorAware<Integer> {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	

	public Optional<Integer> getCurrentAuditor() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authRequest.getPrimary_email(), authRequest.getPassword())
//        );

		if (authentication == null || !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
			return null;
		}

		return Optional.ofNullable(((CustomUserDetails) authentication.getPrincipal()).getGlobal_user_id());
	}
}