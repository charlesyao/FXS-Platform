package com.fxs.platform.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletWebRequest;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CaseQuestionAnswerRelRepository;
import com.fxs.platform.repository.CasesRepository;
import com.fxs.platform.service.CasesService;
import com.fxs.platform.service.FalltypusService;
import com.fxs.platform.utils.CaseManager;
import com.fxs.platform.utils.CaseType;
import com.fxs.platform.utils.SessionVariableManager;
import com.fxs.platform.utils.SystemConstants;
import com.fxs.platform.utils.UserManager;

@Controller
@RequestMapping("/user")
public class DashboardController {
	
	@Autowired
    HttpSession session;
	
	@Autowired
	CaseQuestionAnswerRelRepository caseQuestionAnswerRelRepository;
	
	@Autowired
	CasesRepository caseRepository;
	
	@Autowired
	CasesService casesService;
	
	@Autowired
	FalltypusService falltypusService;
	
	/**
	 * 用户登录成功后公共页面跳转
	 * 
	 * @param authentication
	 * @param map
	 * @param request
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/dashboard")
	public String dashboard(
				Authentication authentication, 
				ModelMap map, 
				ServletWebRequest request,
				@RequestParam(value = "page", defaultValue = "0") Integer page,
                @RequestParam(value = "size", defaultValue = "5") Integer size) throws Exception {
		
		String target = "";
		
		Object caseInSession = session.getAttribute(SystemConstants.GEN_CASES);
		
		/**
		 * 针对未登录的用户提交的案件信息特殊处理
		 * 1. 如果用户未登录，系统会在提交案件的最后一步跳转到此处，
		 * 自动从session中获取所有的填写的案件信息并做保存，包括案件基本信息以及与之对应的问题答案信息
		 */
		if (! ObjectUtils.isEmpty(caseInSession)) {
			
			Cases newCase = CaseManager.saveCase((Cases)caseInSession, session, caseRepository);
			CaseManager.saveCaseQuestionRel(newCase, session, caseQuestionAnswerRelRepository);
		}
		
		if(ObjectUtils.isEmpty(authentication)) {
			return "redirect:/";
		}

		/*Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}*/

		if (UserManager.isLawyer(UserManager.getRoles())) {
			Sort sort = new Sort(Sort.Direction.DESC, "id");
		    Pageable pageable = new PageRequest(page, size, sort);
		    Page<CasesDto> myBidCases=casesService.findAll(CaseType.LAWSUIT.getType(), pageable);
		    
		    map.addAttribute("firstLevelFalltypus", falltypusService.findFirstLevelFalltypus());
		    
		    if(!ObjectUtils.isEmpty(session.getAttribute(SystemConstants.SEARCH_FROM_KEY)) 
		    		&& session.getAttribute(SystemConstants.SEARCH_FROM_KEY).equals(SystemConstants.SEARCH_FROM_LAWYER_DASHBOARD)) {
		    } else {
		    	session.setAttribute("pageableData", myBidCases);
		    }
		    
			target = "lawyer_dashboard";
		} else if (UserManager.isAdmin(UserManager.getRoles())) {
			
			target = "admin_dashboard";
		} else if (UserManager.isUser(UserManager.getRoles())) {
			
			target = "litigant_dashboard";
		} else {
			
			target = "/accessDenied";
		}
		
		SessionVariableManager.clearSession(session);
		
		return target;
	}
}
