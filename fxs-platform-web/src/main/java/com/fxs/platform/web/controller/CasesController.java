package com.fxs.platform.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxs.platform.domain.CaseFeedbackInfo;
import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.DetailedInquiry;
import com.fxs.platform.domain.Reservation;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CaseFeedbackInfoRepository;
import com.fxs.platform.repository.CaseQuestionAnswerRelRepository;
import com.fxs.platform.repository.CityRepository;
import com.fxs.platform.repository.DetailedInquiryRepository;
import com.fxs.platform.repository.FalltypusRepository;
import com.fxs.platform.repository.UserRepository;
import com.fxs.platform.repository.condition.CasesCondition;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.CasesService;
import com.fxs.platform.service.DetailedInquiryService;
import com.fxs.platform.utils.CaseManager;
import com.fxs.platform.utils.CaseStatus;
import com.fxs.platform.utils.PageWrapper;
import com.fxs.platform.utils.ResponseCodeEnum;
import com.fxs.platform.utils.SystemConstants;

@Controller
public class CasesController {

	@Autowired
	CasesService casesService;
	
	@Autowired
    HttpSession session;
	
	@Autowired
	CaseQuestionAnswerRelRepository caseQuestionAnswerRelRepository;
	
	@Autowired
	FalltypusRepository falltypusRepository;
	
	@Autowired
	DetailedInquiryRepository detailedInquiryRepository;
	
	@Autowired
	DetailedInquiryService detailedInquiryService;
	
	@Autowired
	CaseFeedbackInfoRepository caseFeedbackInfoRepository;
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	CityRepository cityRepository;
	
	/**
	 * 当事人提交电话咨询信息
	 * 
	 * @see com.fxs.platform.utils.ConsultationType
	 * @param consultation
	 * @return
	 */
	@PostMapping("/public/case/reservation")
	@ResponseBody
	public ResponseMessage<Reservation> create(@Valid @RequestBody Reservation reservation) {
		
		return Result.success(casesService.create(reservation));
	}

	/**
	 * 提交案件信息，包括免费法律咨询，打官司案件
	 * 
	 * @param cases
	 * @return
	 */
	@PostMapping("/public/case")
	@ResponseBody
	public ResponseMessage<String> create(@Valid @RequestBody Cases cases) {
		String target = null;
		
		session.setAttribute(SystemConstants.GEN_CASES, cases);
		
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			target = "/user/signIn";
		} else {
			
			target = "/user/dashboard";
		}
		
		return Result.success(target);
	}

	/**
	 * 根据不同类型获取案件 
	 * 
	 * @return
	 */
	@GetMapping("/user/case")
	public String query(
						CasesCondition condition,
						ModelMap map,
						@RequestParam(value = "page", defaultValue = "0") Integer page,
		                @RequestParam(value = "size", defaultValue = "5") Integer size) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page, size, sort);
	    
	    Page<CasesDto> cases = casesService.query(condition, pageable);
	    
	    
	    PageWrapper<CasesDto> pageWrapper = new PageWrapper<CasesDto>(cases, condition.getRequestFrom());
	    map.addAttribute("pageableData", pageWrapper.getContent());
        map.addAttribute("page", pageWrapper);
        
        
	    
	    if(!ObjectUtils.isEmpty(condition.getSearchFrom())
	    		&& (condition.getSearchFrom().equals(SystemConstants.SEARCH_FROM_LAWYER_DASHBOARD)
	    				|| condition.getSearchFrom().equals(SystemConstants.SEARCH_FROM_LAWYER_CASEPOOL))) {
	    	
	    	session.setAttribute(SystemConstants.SEARCH_FROM_LAWYER_DASHBOARD, SystemConstants.SEARCH_FROM_LAWYER_DASHBOARD);
	    	session.setAttribute(SystemConstants.SEARCH_FROM_LAWYER_CASEPOOL, SystemConstants.SEARCH_FROM_LAWYER_CASEPOOL);
	    	session.setAttribute(SystemConstants.CASE_DATASET_WITH_FILTER_CONDITION, condition);
	    	
	    	return "lawyer_dashboard :: lawsuitsBlock-fragment";
	    }
	    
	    return "lawyer_case_pool :: consultingBlock-fragment";
	}


	/**
	 * 查看case详细信息
	 * 
	 * @param caseId
	 * @return
	 */
	@GetMapping("/user/case/{userRole}/{type}/viewDetail/{caseId}")
	public String viewDetail(@PathVariable String userRole, @PathVariable String type, @PathVariable String caseId, ModelMap map) {
		String target = "";
		Cases currentCase = casesService.findByCaseId(caseId);
		
		if (userRole.equals("lawyer")) {
			currentCase.setIsRead(CaseStatus.READ.getStatus());
			
			casesService.create(currentCase);
		}
		
		map.addAttribute("caseDetailInfo", CaseManager.caseWrapperDetail(userRole, currentCase, caseQuestionAnswerRelRepository, 
				falltypusRepository, detailedInquiryRepository, caseFeedbackInfoRepository, cityRepository, session));
		
		if (userRole.equals("litigant")) {
			//consulting
			if (type.equals("consulting")) {
				target = "litigant_consulting_free_detail";
			} else {
				//lawsuit
				target = "litigant_lawsuit_detail";
			}
		} else if (userRole.equals("lawyer")) {
			
			if (type.equals("consulting")) {
				target = "lawyer_consulting_detail";
			} else {
				//lawsuit
				target = "lawyer_lawsuit_detail";
			}
		}
		return target;
	}

	/**
	 * 更新case信息
	 * 
	 * @param caseId
	 * @param cases
	 * @return
	 */
	@PutMapping("/user/case/update/{caseId}")
	@ResponseBody
	public ResponseMessage<Cases> update(@PathVariable String caseId, @Valid @RequestBody CaseFeedbackInfo cases) {
		return Result.success(casesService.update(caseId, cases));
	}
	
	/**
	 * 对案件添加追问
	 * 
	 * @param caseId
	 * @param cases
	 * @return
	 */
	@PutMapping("/user/case/addDetailedInquiry/{caseId}")
	@ResponseBody
	public ResponseMessage<Integer> addDetailedInquiry(@PathVariable String caseId, @Valid @RequestBody Cases cases) {
		DetailedInquiry di = detailedInquiryService.save(caseId, cases.getDetailedInquirys());
		
		if (ObjectUtils.isEmpty(di)) {
			return Result.success(ResponseCodeEnum.ERROR.getCode());
		}

		return Result.success(ResponseCodeEnum.SUCCESS.getCode());
	}
}
