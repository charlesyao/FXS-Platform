package com.fxs.platform.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.Question;
import com.fxs.platform.domain.User;
import com.fxs.platform.domain.UserProfile;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.dto.QuestionDto;
import com.fxs.platform.repository.condition.CasesCondition;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.AnswerService;
import com.fxs.platform.service.CasesService;
import com.fxs.platform.service.FalltypusService;
import com.fxs.platform.service.QuestionService;
import com.fxs.platform.service.RoleService;
import com.fxs.platform.utils.CaseType;
import com.fxs.platform.utils.PageWrapper;
import com.fxs.platform.utils.SessionVariableManager;
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
		List<UserProfile> userProfile = roleService.findAll();
		session.setAttribute("roles", userProfile);
		return userProfile;
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

	/**
	 * 所有路由跳转配置
	 * 
	 * @param userRole
	 * @param caseType
	 * @param action
	 * @param condition
	 * @param map
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/{userRole}/case/{caseType}/{action}")
	public String router(@PathVariable String userRole, 
							 @PathVariable String caseType, 
							 @PathVariable String action, 
							 CasesCondition condition,
							 ModelMap map,
							 @RequestParam(value = "page", defaultValue = "0") Integer page,
				             @RequestParam(value = "size", defaultValue = "5") Integer size) {
		
		String target = "";
		
		if(userRole.equals("public")) {//公共打官司法律咨询路由
			if (caseType.equals("consulting")) {//法律咨询总路由
				if (action.equals("free")) {//免费法律咨询路由
					
					//map.addAttribute("firstLevelFalltypus", falltypusList);
					session.setAttribute("firstLevelFalltypus", falltypusService.findFirstLevelFalltypus());
					
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
					Sort sort = new Sort(Sort.Direction.DESC, "id");
				    Pageable pageable = new PageRequest(page, size, sort);
				    session.setAttribute("pageableData", casesService.query(condition, pageable));
					
					target = "litigant_consulting_free";
				} else if (action.equals("phone")) {
					//获取所有电话咨询信息列表
					Sort sort = new Sort(Sort.Direction.DESC, "id");
				    Pageable pageable = new PageRequest(page, size, sort);
				    session.setAttribute("pageableData", casesService.findAllReservation(pageable));
					
					target = "litigant_consulting_phone";
				}
			} else if (caseType.equals("lawsuit")) {
				//获取当事人的打官司信息列表
				Sort sort = new Sort(Sort.Direction.DESC, "id");
			    Pageable pageable = new PageRequest(page, size, sort);
			    session.setAttribute("pageableData", casesService.query(condition, pageable));
				
				target = "litigant_lawsuit";
			}
		} else if (userRole.equals("lawyer")) {//律师页面路由
			
		}
		
		return target;
	}

	@GetMapping("/lawyer/case_pool")
	public String casePool(
				ModelMap map,
				CasesCondition condition,
				HttpServletRequest request,
				@RequestParam(value = "page", defaultValue = "0") Integer page,
                @RequestParam(value = "size", defaultValue = "5") Integer size) {
		
		if (SystemConstants.REQUEST_FROM_LAWYER_USER_CENTER.equals(condition.getRequestFrom())) {
			SessionVariableManager.clearSession(session);
		}
		
		Sort sort = new Sort(Sort.Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page, size, sort);
	    PageWrapper<CasesDto>  pageWrapper;
	    
	    CasesCondition originalCondition = (CasesCondition)session.getAttribute(SystemConstants.CASE_DATASET_WITH_FILTER_CONDITION);
	    if(!ObjectUtils.isEmpty(session.getAttribute(SystemConstants.SEARCH_FROM_LAWYER_CASEPOOL)) 
	    		&& session.getAttribute(SystemConstants.SEARCH_FROM_LAWYER_CASEPOOL).equals(SystemConstants.SEARCH_FROM_LAWYER_CASEPOOL)) {
	    	Page<CasesDto> cases = casesService.query(originalCondition, pageable);
	    	pageWrapper = new PageWrapper<CasesDto>(cases, originalCondition.getRequestFrom());

	    } else {
		    Page<CasesDto> myBidCases=casesService.findAll(CaseType.LAWSUIT.getType(), pageable);
		    pageWrapper = new PageWrapper<CasesDto>(myBidCases, request.getRequestURI());
	    }
	    
	    map.addAttribute("pageableData", pageWrapper.getContent());
        map.addAttribute("page", pageWrapper);
        
        
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
	
	/**
	 * 获取案件类型
	 * 
	 * @return
	 */
	@GetMapping("/admin/falltypus/getLevelOneData")
	public String getFirstLevelFalltypus(ModelMap map) {
		map.addAttribute("levelOneFalltypusList", falltypusService.findFirstLevelFalltypus());
		return "levelOneFalltypusList";
	}

	/**
	 * 获取案件类型
	 * 
	 * @return
	 */
	@GetMapping("/admin/falltypus/getLevelTwoData/{fId}")
	public String getSecondLevelFalltypus(@PathVariable String fId, ModelMap map) {
		map.addAttribute("levelTwoFalltypusList", falltypusService.findSubFalltypusByParentId(fId));
		return "levelTwoFalltypusList";
	}
	
	/**
	 * 删除案件类型
	 * 
	 * @param fId
	 * @return
	 */
	@DeleteMapping("/admin/falltypus/delete/{fId}")
	@ResponseBody
	public ResponseMessage<String>  delete(@PathVariable String fId) {
		falltypusService.delete(fId);
		return Result.success("success");
	}
}
