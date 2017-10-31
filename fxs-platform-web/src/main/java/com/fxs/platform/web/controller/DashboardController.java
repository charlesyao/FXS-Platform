package com.fxs.platform.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Charles
 *
 */
@Controller
@RequestMapping("/user")
public class DashboardController {
	@GetMapping("/dashboard")
	public String dashboard(Authentication user, ModelMap map) throws Exception {
		int usertype = 1;
		String target = StringUtils.EMPTY;
		switch (usertype) {
		case 0:
			target = "litigant_dashboard";
			break;
		case 1:
			target = "lawer_dashboard";
			break;
		case 2:
			target = "admin_dashboard";
			break;
		}

		return target;
	}
}
