package com.fxs.platform.utils;

import javax.servlet.http.HttpSession;

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
}
