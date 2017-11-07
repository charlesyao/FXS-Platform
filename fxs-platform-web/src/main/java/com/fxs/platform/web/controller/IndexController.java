package com.fxs.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fxs.platform.domain.Role;
import com.fxs.platform.service.RoleService;

@Controller
@SessionAttributes("roles")
public class IndexController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/")
	public String index(ModelMap map) throws Exception {
		return "formsubmit";
	}
	
	@ModelAttribute("roles")
	public List<Role> initializeProfiles() {
		return roleService.findAll();
	}
}
