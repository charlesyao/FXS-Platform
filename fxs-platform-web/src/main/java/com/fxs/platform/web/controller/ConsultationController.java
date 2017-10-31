package com.fxs.platform.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.domain.Consultation;
import com.fxs.platform.dto.ConsultationDto;
import com.fxs.platform.repository.condition.ConsultationCondition;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.ConsultationService;

/**
 * 法律咨询接口
 * 
 * @author Charles
 *
 */
@RestController
@RequestMapping("/consultation")
public class ConsultationController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	ConsultationService consultationService;

	/**
	 * 提交法律咨询信息
	 * 
	 * @see com.fxs.platform.utils.ConsultationType
	 * @param consultation
	 * @return
	 */
	@PostMapping
	public ResponseMessage<Consultation> create(@Valid @RequestBody Consultation consultation) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.save.success"),
				consultationService.create(consultation));
	}

	/**
	 * 分页查找咨询信息
	 * 
	 * @see com.fxs.platform.utils.ConsultationType
	 * @see com.fxs.platform.utils.CaseStatus
	 * 
	 * @param type
	 * @return
	 */
	@GetMapping
	public ResponseMessage<Page<ConsultationDto>> query(ConsultationCondition condition, Pageable pageable) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.get.success"),
				consultationService.query(condition, pageable));
	}

	/**
	 * 更新法律咨询信息
	 * 
	 * @param consultationId
	 * @param consultation
	 * @return
	 */
	@PutMapping("/{consultationId}")
	public ResponseMessage<Consultation> update(@PathVariable String consultationId,
			@Valid @RequestBody Consultation consultation) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.get.success",
				new Object[] { consultationId }), consultationService.update(consultationId, consultation));
	}
}
