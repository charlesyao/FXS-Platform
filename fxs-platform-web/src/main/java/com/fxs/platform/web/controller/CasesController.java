package com.fxs.platform.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.DetailedInquiry;
import com.fxs.platform.domain.Reservation;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CaseQuestionAnswerRelRepository;
import com.fxs.platform.repository.DetailedInquiryRepository;
import com.fxs.platform.repository.FalltypusRepository;
import com.fxs.platform.repository.condition.CasesCondition;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.CasesService;
import com.fxs.platform.service.DetailedInquiryService;
import com.fxs.platform.utils.CaseManager;
import com.fxs.platform.utils.ResponseCodeEnum;
import com.fxs.platform.utils.SystemConstants;

@Controller
public class CasesController {
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

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
	 * 获取所有案件，针对律师的案件池
	 * 
	 * @return
	 */
	@GetMapping("/user/case")
	@ResponseBody
	public ResponseMessage<List<CasesDto>> query() {
		return Result.success(casesService.findAll());
	}

	/**
	 * 根据不同类型获取案件 
	 * 
	 * @return
	 */
	@GetMapping("/user/case/type/{caseType}")
	@ResponseBody
	public ResponseMessage<List<CasesDto>> queryByType(@PathVariable String caseType, ModelMap map) {
		return Result.success(casesService.findByType(caseType));
	}
	
	/**
	 * 根据状态获取案件
	 * 
	 * @see com.fxs.platform.utils.CaseStatus
	 * 
	 * @param status
	 * @return
	 */
	@GetMapping("/user/case/status/{status}")
	@ResponseBody
	public ResponseMessage<List<Cases>> queryByStatus(@PathVariable String status) {
		return Result.success(casesService.findByStatus(status));
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
		map.addAttribute("caseDetailInfo", CaseManager.caseWrapper(
				casesService.findByCaseId(caseId), caseQuestionAnswerRelRepository, falltypusRepository, detailedInquiryRepository));
		
		if (userRole.equals("litigant")) {
			//consulting
			if (type.equals("consulting")) {
				target = "litigant_consulting_free_detail";
			} else {
				//lawsuit
				target = "litigant_lawsuit_detail";
			}
		} else if (userRole.equals("lawyer")) {
			target = "lawyer_case_detail";
		}
		return target;
	}

	/**
	 * 更新case信息
	 * @param caseId
	 * @param cases
	 * @return
	 */
	@PutMapping("/user/case/update/{caseId}")
	@ResponseBody
	public ResponseMessage<Cases> update(@PathVariable String caseId, @Valid @RequestBody Cases cases) {
		return Result.success(casesService.update(caseId, cases));
	}
	
	@PutMapping("/user/case/addDetailedInquiry/{caseId}")
	@ResponseBody
	public ResponseMessage<Integer> addDetailedInquiry(@PathVariable String caseId, @Valid @RequestBody Cases cases) {
		DetailedInquiry di = detailedInquiryService.save(caseId, cases.getDetailedInquirys());
		
		if (ObjectUtils.isEmpty(di)) {
			return Result.success(ResponseCodeEnum.ERROR.getCode());
		}

		return Result.success(ResponseCodeEnum.SUCCESS.getCode());
	}
	
	@PutMapping("/user/case/update/{caseId}/{statusCode}")
	@ResponseBody
	public ResponseMessage<Integer> resolve(@PathVariable String caseId, @PathVariable String statusCode) {
		casesService.updateStatus(statusCode, caseId);
		return Result.success(ResponseCodeEnum.SUCCESS.getCode());
	}
	
	@GetMapping("/user/case/multicondition")
	@ResponseBody
	public ResponseMessage<List<CasesDto>> query(CasesCondition condition, Pageable pageable) {
		return Result.success(casesService.query(condition, pageable));
	}
}
