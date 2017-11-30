package com.fxs.platform.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.Question;
import com.fxs.platform.domain.User;
import com.fxs.platform.domain.UserProfile;
import com.fxs.platform.dto.QuestionDto;
import com.fxs.platform.repository.condition.CasesCondition;
import com.fxs.platform.service.AnswerService;
import com.fxs.platform.service.CasesService;
import com.fxs.platform.service.FalltypusService;
import com.fxs.platform.service.QuestionService;
import com.fxs.platform.service.RoleService;
import com.fxs.platform.utils.CaseType;
import com.fxs.platform.utils.SystemConstants;

@Controller
@SessionAttributes("roles")
public class RouterController {
	@Autowired
	FalltypusService falltypusService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	CasesService casesService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	HttpSession session;

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return roleService.findAll();
	}

	@GetMapping("/")
	public String index(ModelMap map) throws Exception {
		return "index";
	}
	
	@GetMapping("/invalid-session")
	public String invalidSession() throws Exception {
		return "invalidSession";
	}
	
	@GetMapping("/accessForbidden")
	public String accessForbidden() throws Exception {
		return "accessForbidden";
	}

	@GetMapping("/user/signIn")
	public String userSignIn() {
		return "userSignIn";
	}

	@GetMapping("/user/register")
	public String userRegister(@ModelAttribute(value = "user") User user, ModelMap map) {
		
		if (! ObjectUtils.isEmpty(session.getAttribute(SystemConstants.DUPLICATE_USER))) {
			map.addAttribute(SystemConstants.DUPLICATE_USER, session.getAttribute(SystemConstants.DUPLICATE_USER));
		}
		
		session.removeAttribute(SystemConstants.DUPLICATE_USER);
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
							 CasesCondition condition,
							 Pageable pageable,
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
				}
				
			} else if (caseType.equals("lawsuit")) {
				if (action.equals("lawyer")) {//找律师路由

					target = "public_lawsuit_lawyer";
				} else if (action.equals("start")) {//开始找律师打官司路由

					map.addAttribute("firstLevelFalltypus", falltypusService.findFirstLevelFalltypus());
					target = "public_lawsuit_lawyer_step1";
				} else if (action.equals("next")) {
					
					List<QuestionDto> questionDtoList = new ArrayList<QuestionDto>();
					
					List<Question> optionalQuestionList = questionService.findOptionalQuestions();
					
					for (Question question : optionalQuestionList) {
						QuestionDto questionDto = new QuestionDto();
						List<Answer> answerList = answerService.getAllAnswerByQuestionId(question.getId());
						
						questionDto.setQuestion(question);
						questionDto.setAnswers(answerList);
						
						questionDtoList.add(questionDto);
					}
					
					map.addAttribute("optionalQuestions", questionDtoList);
					
					target = "public_lawsuit_lawyer_step4";
				} else if (action.equals("self_service")) {//自助打官司

					target = "public_lawsuit_self_service";
				}
			}
		} else if (userRole.equals("litigant")) {//当事人页面路由
			if(caseType.equals("consulting")) {
				if (action.equals("free")) {
					//获取免费咨询信息列表
					map.addAttribute("myFreeConsultings", casesService.query(condition, pageable));
					target = "litigant_consulting_free";
				} else if (action.equals("phone")) {
					//获取所有电话咨询信息列表
					map.addAttribute("myPhoneConsultings", casesService.findAllReservation());
					target = "litigant_consulting_phone";
				}
			} else if (caseType.equals("lawsuit")) {
				//获取当事人的打官司信息列表
				map.addAttribute("myLawsuit", casesService.query(condition, pageable));
				target = "litigant_lawsuit";
			}
		} else if (userRole.equals("lawyer")) {//律师页面路由
			
		}
		
		return target;
	}

	@GetMapping("/lawyer/case_pool")
	public String casePool(ModelMap map) {
		
		map.addAttribute("freeConsultings", casesService.findAll(CaseType.CONSULTING.getType()));
		
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
