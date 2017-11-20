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

import com.fxs.platform.domain.Question;
import com.fxs.platform.domain.User;
import com.fxs.platform.domain.UserProfile;
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
	public String userRegister(@ModelAttribute(value = "user") User user, BindingResult bindingResult) {
		return "userRegister";
	}

	@GetMapping("/lawyer/signIn")
	public String lawyerSignIn() {
		return "lawyerSignIn";
	}

	@GetMapping("/{userRole}/case/{caseType}/{action}")
	public String router(@PathVariable String userRole, 
							 @PathVariable String caseType, 
							 @PathVariable String action, 
							 ModelMap map) {
		
		String target = "";
		
		if(userRole.equals("public")) {//公共打官司法律咨询路由
			if (caseType.equals("consulting")) {//法律咨询总路由
				if (action.equals("free")) {//免费法律咨询路由
					
					map.addAttribute("firstLevelFalltypus", falltypusService.findFirstLevelFalltypus());
					target = "public_consulting_free";
				} else if (action.equals("phone")) {//电话咨询路由
					
					target = "public_consulting_phone";
				} else if (action.equals("next")) {

					target = "public_consulting_free_step3";
				} else if (action.equals("submit")) {//提交免费法律咨询
					if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
						target = "redirect:/user/signIn";
					} else {
						target = "redirect:/user/dashboard";
					}
				}
				
			} else if (caseType.equals("lawsuit")) {
				if (action.equals("lawyer")) {//找律师路由

					target = "public_lawsuit_lawyer";
				} else if (action.equals("start")) {//开始找律师打官司路由

					map.addAttribute("firstLevelFalltypus", falltypusService.findFirstLevelFalltypus());
					target = "public_lawsuit_lawyer_step1";
				} else if (action.equals("next")) {

					target = "public_lawsuit_lawyer_step4";
				} else if (action.equals("submit")) {//提交找律师打官司请求，如果未登录则跳转到登录界面同时提交信息，否则直接跳转到dashboard
					if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
						target = "redirect:/user/signIn";
					} else {
						target = "redirect:/user/dashboard";
					}
				} else if (action.equals("self_service")) {//自助打官司

					target = "public_lawsuit_self_service";
				}
			}
		} else if (userRole.equals("litigant")) {//当事人页面路由
			if(caseType.equals("consulting")) {
				if (action.equals("free")) {
					target = "litigant_consulting_free";
				} else if (action.equals("phone")) {
					target = "litigant_consulting_phone";
				}
			} else if (caseType.equals("lawsuit")) {
				target = "litigant_lawsuit";
			}

		} else if (userRole.equals("lawyer")) {//律师页面路由
			
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

	@GetMapping("/question/create")
	public String createQuestionnaire(@ModelAttribute(value = "question") Question question,
			BindingResult bindingResult) {
		return "addQuestion";
	}
}
