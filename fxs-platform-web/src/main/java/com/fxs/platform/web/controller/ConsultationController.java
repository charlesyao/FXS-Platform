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

import com.fxs.platform.domain.Consultation;
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
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.save"),
				consultationService.create(consultation));
	}

	/**
	 * 根据类型查找法律咨询信息
	 * 
	 * @see com.fxs.platform.utils.ConsultationType
	 * @param consultationType
	 * @return
	 */
	@GetMapping("/{consultationType}")
	public ResponseMessage<List<Consultation>> findByType(@PathVariable String consultationType) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.lawsuit.get",
				new Object[] { consultationType }), consultationService.findByType(consultationType));
	}

	/**
	 * 根据状态查找法律咨询信息
	 * 
	 * @see com.fxs.platform.utils.CaseStatus
	 * @param consultationStatus
	 * @return
	 */
	@GetMapping("/{consultationStatus}")
	public ResponseMessage<List<Consultation>> findByStatus(@PathVariable String consultationStatus) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.get",
				new Object[] { consultationStatus }), consultationService.findByStatus(consultationStatus));
	}

	/**
	 * 查看法律咨询详细信息
	 * 
	 * @param consultationId
	 * @return
	 */
	@GetMapping("/{consultationId}")
	public ResponseMessage<Consultation> findByCaseId(@PathVariable String consultationId) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.get",
				new Object[] { consultationId }), consultationService.findByConsultationId(consultationId));
	}

	/**
	 * 查询所有法律咨询信息
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseMessage<List<Consultation>> query() {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] {}),
				consultationService.findAll());
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
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.get",
				new Object[] { consultationId }), consultationService.update(consultationId, consultation));
	}
}
