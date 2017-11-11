package com.fxs.platform.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

@Controller
@RequestMapping("/user")
public class DashboardController {
	@GetMapping("/dashboard")
	public String dashboard(Authentication authentication, ModelMap map, ServletWebRequest request) throws Exception {
		
		System.out.println(request.getRequest().getSession().getAttribute("SESSION_KEY_FOR_QUESTIONNAIRE"));
		
		String target = "";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (isLawyer(roles)) {
			target = "/lawyer_dashboard";
		} else if (isAdmin(roles)) {
			target = "/admin_dashboard";
		} else if (isUser(roles)) {
			target = "/litigant_dashboard";
		} else {
			target = "/accessDenied";
		}

		return target;
	}

	private boolean isUser(List<String> roles) {
		if (roles.contains("ROLE_USER")) {
			return true;
		}
		return false;
	}

	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}

	private boolean isLawyer(List<String> roles) {
		if (roles.contains("ROLE_LAWYER")) {
			return true;
		}
		return false;
	}
}
