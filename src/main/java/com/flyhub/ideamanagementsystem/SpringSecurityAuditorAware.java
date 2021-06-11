package com.flyhub.ideamanagementsystem;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;

import com.flyhub.ideamanagementsystem.entities.CustomUserDetails;

class SpringSecurityAuditorAware implements AuditorAware<Integer> {
	
	

	public Optional<Integer> getCurrentAuditor() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
			return null;
		}

		return Optional.ofNullable(((CustomUserDetails) authentication.getPrincipal()).getGlobal_user_id());
	}
}