package com.fxs.platform.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.CasesService;

@RestController
@RequestMapping("/case")
public class CasesController {
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	CasesService casesService;

	/**
	 * 提交法律咨询信息
	 * 
	 * @param cases
	 * @return
	 */
	@PostMapping
	public ResponseMessage<Cases> create(@Valid @RequestBody Cases cases) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.save"),
				casesService.create(cases));
	}

	/**
	 * 获取所有案件
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseMessage<List<CasesDto>> query() {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] {}),
				casesService.findAll());
	}

	/**
	 * 根据法律咨询类型获取 type: 免费/电话 例如： 当事人查询电话咨询或者自助打官司案件 或者免费法律咨询或者找律师打的官司
	 * 
	 * @see com.fxs.platform.utils.CaseType
	 * @see com.fxs.platform.utils.CaseSubType
	 * 
	 * @param type
	 * @return
	 */
	/*@GetMapping("/{type}/{subType}")
	public ResponseMessage<List<CasesDto>> query(@PathVariable String type, @PathVariable String subType) {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] { type }),
				casesService.findByTypeAndSubType(type, subType));
	}*/

	/**
	 * 根据状态获取case
	 * 
	 * @see com.fxs.platform.utils.CaseStatus
	 * 
	 * @param status
	 * @return
	 */
	@GetMapping("/{status}")
	public ResponseMessage<List<CasesDto>> query(@PathVariable String status) {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] { status }),
				casesService.findByStatus(status));
	}

	/**
	 * 查看case详细信息
	 * 
	 * @param caseId
	 * @return
	 */
	@GetMapping("/{caseId}")
	public ResponseMessage<Cases> viewDetail(@PathVariable String caseId) {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] { caseId }),
				casesService.findByCaseId(caseId));
	}

	@PutMapping("/{caseId}")
	public ResponseMessage<Cases> update(@PathVariable String caseId, @Valid @RequestBody Cases cases) {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] { caseId }),
				casesService.update(caseId, cases));
	}
}
