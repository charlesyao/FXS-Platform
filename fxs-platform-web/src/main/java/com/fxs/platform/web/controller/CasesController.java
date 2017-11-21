package com.fxs.platform.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.Reservation;
import com.fxs.platform.domain.User;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CaseQuestionAnswerRelRepository;
import com.fxs.platform.repository.CasesRepository;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.CasesService;
import com.fxs.platform.utils.CaseManager;
import com.fxs.platform.utils.SystemConstants;

@RestController
@RequestMapping("/public/case")
public class CasesController {
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	CasesService casesService;
	
	@Autowired
    HttpSession session;
	
	@Autowired
	CaseQuestionAnswerRelRepository repository;
	
	@Autowired
	CasesRepository caseRepository;
	
	/**
	 * 当事人提交电话咨询信息
	 * 
	 * @see com.fxs.platform.utils.ConsultationType
	 * @param consultation
	 * @return
	 */
	@PostMapping("/reservation")
	public ResponseMessage<Reservation> create(@Valid @RequestBody Reservation reservation) {
		
		return Result.success(casesService.create(reservation));
	}
	
	/**
	 * 查询当事人的所有的电话咨询自信息
	 * 
	 * @see com.fxs.platform.utils.ConsultationType
	 * @param consultation
	 * @return
	 */
	@GetMapping("/reservation")
	public ResponseMessage<List<Reservation>> getreservation() {
		
		return Result.success(casesService.findAllReservation());
	}

	/**
	 * 提交案件信息，包括免费法律咨询，打官司案件
	 * 
	 * @param cases
	 * @return
	 */
	@PostMapping
	public String create(@Valid @RequestBody Cases cases) {
		String target = null;
		
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			target = "redirect:/user/signIn";
		} else {
			CaseManager.saveCase(cases, session, caseRepository);
			CaseManager.saveCaseQuestionRel(session, repository);
			
			target = "redirect:/user/dashboard";
		}
		
		return target;
	}

	/**
	 * 获取所有案件，针对律师的案件池
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseMessage<List<CasesDto>> query() {
		return Result.success(casesService.findAll());
	}

	/**
	 * 根据不同类型获取案件 
	 * 
	 * @return
	 */
	@GetMapping("/type/{caseType}")
	public ResponseMessage<List<CasesDto>> queryByType(@PathVariable String caseType) {
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
	@GetMapping("/status/{status}")
	public ResponseMessage<List<CasesDto>> queryByStatus(@PathVariable String status) {
		return Result.success(casesService.findByStatus(status));
	}

	/**
	 * 查看case详细信息
	 * 
	 * @param caseId
	 * @return
	 */
	@GetMapping("/{caseId}")
	public ResponseMessage<Cases> viewDetail(@PathVariable String caseId) {
		return Result.success(casesService.findByCaseId(caseId));
	}

	/**
	 * 更新case信息
	 * @param caseId
	 * @param cases
	 * @return
	 */
	@PutMapping("/{caseId}")
	public ResponseMessage<Cases> update(@PathVariable String caseId, @Valid @RequestBody Cases cases) {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] { caseId }),
				casesService.update(caseId, cases));
	}
}
