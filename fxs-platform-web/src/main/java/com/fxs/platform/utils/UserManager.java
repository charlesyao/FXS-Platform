package com.fxs.platform.utils;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.User;

public class UserManager {
	
	public static String getSessionUser(HttpSession session) {
		String userId = "-1";
		
		Object userInSession = session.getAttribute(SystemConstants.USER_INFO);
		
		if (! ObjectUtils.isEmpty(userInSession)) {
			userId = String.valueOf(((User)userInSession).getId());
		}
		
		return userId;
	}
	
	public static String getPrincipal() {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}
	
	public static boolean isUser(List<String> roles) {
		if (roles.contains("ROLE_USER")) {
			return true;
		}
		return false;
	}

	public static boolean isAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}

	public static boolean isLawyer(List<String> roles) {
		if (roles.contains("ROLE_LAWYER")) {
			return true;
		}
		return false;
	}
}
