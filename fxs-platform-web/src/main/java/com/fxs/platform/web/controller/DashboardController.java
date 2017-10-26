package com.fxs.platform.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class DashboardController {
	@GetMapping("/dashboard")
	public String dashboard(ModelMap map) throws Exception {
		map.addAttribute("host", "http://fxs.com");
		return "dashboard";
	}
	
	@GetMapping("/signIn")
	public String login() {
		return "signIn";
	}
}
