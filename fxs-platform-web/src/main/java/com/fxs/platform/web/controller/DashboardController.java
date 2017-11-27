package com.fxs.platform.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.repository.CaseQuestionAnswerRelRepository;
import com.fxs.platform.repository.CasesRepository;
import com.fxs.platform.service.CasesService;
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
	
	
	@GetMapping("/dashboard")
	public String dashboard(Authentication authentication, ModelMap map, ServletWebRequest request) throws Exception {
		
		String target = "";
		
		Object caseInSession = session.getAttribute(SystemConstants.GEN_CASES);
		
		if (! ObjectUtils.isEmpty(caseInSession)) {
			
			Cases newCase = CaseManager.saveCase((Cases)caseInSession, session, caseRepository);
			CaseManager.saveCaseQuestionRel(newCase, session, caseQuestionAnswerRelRepository);
		}
		
		if(ObjectUtils.isEmpty(authentication)) {
			return "redirect:/";
		}

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (UserManager.isLawyer(roles)) {
			map.addAttribute("myBidCases", casesService.findAll(CaseType.LAWSUIT.getType()));
			target = "/lawyer_dashboard";
		} else if (UserManager.isAdmin(roles)) {
			target = "/admin_dashboard";
		} else if (UserManager.isUser(roles)) {
			target = "/litigant_dashboard";
		} else {
			target = "/accessDenied";
		}
		
		SessionVariableManager.clearSession(session);
		
		return target;
	}
}
