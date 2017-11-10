package com.fxs.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fxs.platform.domain.Role;
import com.fxs.platform.service.FalltypusService;
import com.fxs.platform.service.RoleService;

@Controller
@SessionAttributes("roles")
public class RouterController {
	@Autowired
	FalltypusService falltypusService;
	

	@Autowired
	RoleService roleService;
	
	@ModelAttribute("roles")
	public List<Role> initializeProfiles() {
		return roleService.findAll();
	}
	
	@GetMapping("/")
	public String index(ModelMap map) throws Exception {
		return "index";
	}
	
	@GetMapping("/user/signIn")
	public String userSignIn() {
		return "userSignIn";
	}
	
	@GetMapping("/user/register")
	public String userRegister() {
		return "userRegister";
	}
	
	@GetMapping("/lawer/signIn")
	public String lawerSignIn() {
		return "lawerSignIn";
	}
	
	@GetMapping("/lawsuit/litigant")
	public String litigantLawsuit(ModelMap map) {
		return "litigant_lawsuit";
	}
	
	@GetMapping("/lawsuit/{caseType}/{subType}")
	public String publicLawsuit(@PathVariable String caseType, @PathVariable String subType, ModelMap map) {
		String target = "";
		
		if (caseType.equals("public")) {
			if (subType.equals("lawer")) {
				
				target = "public_lawsuit_lawer";
			} else if (subType.equals("self_service")) {
				
				target = "public_lawsuit_self_service";
			}
		}
		
		return target;
	}
	
	@GetMapping("/consultation/{caseType}/{subType}")
	public String free(@PathVariable String caseType, @PathVariable String subType, ModelMap map) {
		String target = "";
		
		if (caseType.equals("litigant")) {
			if (subType.equals("free")) {
				
				target = "litigant_consulting_free";
			} else if (subType.equals("phone")) {
				
				target = "litigant_consulting_phone";
			}
		} else if (caseType.equals("public")) {
			if (subType.equals("free")) {
				
				target = "public_consulting_free";
			} else if (subType.equals("phone")) {
				
				target = "public_consulting_phone";
			}
		}
		
		return target;
	}
	
	@GetMapping("/lawer/case_pool")
	public String casePool() {
		return "lawer_case_pool";
	}
	
	@GetMapping("/falltypus/create/parent")
	public String createParentFalltypus() {
		return "addFalltypus";
	}
	
	@GetMapping("/falltypus/create/sub")
	public String createSubFalltypus(ModelMap map) {
		map.addAttribute("falltypusList", falltypusService.findFirstLevelFalltypus());
		return "addSubFalltypus";
	}
	
	@GetMapping("/questionnaire/create")
	public String createQuestionnaire(ModelMap map) {
		map.addAttribute("falltypusList", falltypusService.findFirstLevelFalltypus());
		return "addQuestionnaireStep1";
	}
}
