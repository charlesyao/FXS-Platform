package com.fxs.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.fxs.platform.domain.Question;
import com.fxs.platform.domain.User;
import com.fxs.platform.domain.UserProfile;
import com.fxs.platform.dto.FalltypusDto;
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
	public List<UserProfile> initializeProfiles() {
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
	public String userRegister(@ModelAttribute(value="user") User user, BindingResult bindingResult) {
		return "userRegister";
	}
	
	@GetMapping("/lawyer/signIn")
	public String lawyerSignIn() {
		return "lawyerSignIn";
	}
	
	@GetMapping("/litigant/lawsuit")
	public String litigantLawsuit(ModelMap map) {
		
		return "litigant_lawsuit";
	}
	
	@GetMapping("/{userRole}/lawsuit/{subType}")//打官司案件
	public String publicLawsuit(@PathVariable String userRole, @PathVariable String subType, ModelMap map, ServletWebRequest request) {
		String target = "";
		
		if (userRole.equals("public")) {
			if (subType.equals("lawyer")) {
				
				target = "public_lawsuit_lawyer";
			} else if (subType.equals("start")) {
				
				map.addAttribute("firstLevelFalltypus" ,falltypusService.findFirstLevelFalltypus());
				target = "public_lawsuit_lawyer_step1";
			} else if (subType.equals("next")) {
				
				target = "public_lawsuit_lawyer_step4";
			} else if (subType.equals("submit")) {
				if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
					target = "redirect:/user/signIn";
				} else {
					User user = (User)request.getRequest().getSession().getAttribute("userInfo");
					target = "redirect:/user/dashboard";
				}
			} else if (subType.equals("self_service")) {
				
				target = "public_lawsuit_self_service";
			}
		}
		
		return target;
	}
	
	@GetMapping("/{userRole}/consultation/{subType}")//法律咨询
	public String free(@PathVariable String userRole, @PathVariable String subType, ModelMap map) {
		String target = "";
		
		if (userRole.equals("litigant")) {
			if (subType.equals("free")) {
				
				target = "litigant_consulting_free";
			} else if (subType.equals("phone")) {
				
				target = "litigant_consulting_phone";
			}
		} else if (userRole.equals("public")) {
			if (subType.equals("free")) {
				
				map.addAttribute("firstLevelFalltypus" ,falltypusService.findFirstLevelFalltypus());
				target = "public_consulting_free";
			} else if (subType.equals("phone")) {
				
				target = "public_consulting_phone";
			} else if (subType.equals("next")) {
				
				target = "public_consulting_free_step3";
			} else if (subType.equals("submit")) {
				if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
					target = "redirect:/user/signIn";
				} else {
					target = "redirect:/user/dashboard";
				}
			}
		}
		
		return target;
	}
	
	@GetMapping("/lawyer/case_pool")
	public String casePool() {
		return "lawyer_case_pool";
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
	public String createQuestionnaire(@ModelAttribute(value="falltypus") FalltypusDto falltypus, BindingResult bindingResult, ModelMap map) {
		map.addAttribute("falltypusList", falltypusService.findFirstLevelFalltypus());
		return "addQuestionnaireStep1";
	}
	
	@GetMapping("/question/create")
	public String createQuestionnaire(@ModelAttribute(value="question") Question question, BindingResult bindingResult) {
		return "addQuestion";
	}
}
