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

import com.fxs.platform.domain.Lawsuit;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.LawsuitService;

/**
 * 打官司接口
 * 
 * @author Charles
 *
 */
@RestController
@RequestMapping("/lawsuit")
public class LawsuitController {
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	LawsuitService lawsuitService;

	/**
	 * 提交打官司信息
	 * 
	 * @see com.fxs.platform.utils.LawsuitType
	 * 
	 * @param lawsuit
	 * @return
	 */
	@PostMapping
	public ResponseMessage<Lawsuit> create(@Valid @RequestBody Lawsuit lawsuit) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.save"),
				lawsuitService.create(lawsuit));
	}

	/**
	 * 根据类型查找官司信息
	 * 
	 * @see com.fxs.platform.utils.LawsuitType
	 * @param type
	 * @return
	 */
	@GetMapping("/{type}")
	public ResponseMessage<List<Lawsuit>> findByType(@PathVariable String type) {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] { type }),
				lawsuitService.findByType(type));
	}

	/**
	 * 根据状态查找官司信息
	 * 
	 * @see com.fxs.platform.utils.CaseStatus
	 * @param lawsuitStatus
	 * @return
	 */
	@GetMapping("/{lawsuitStatus}")
	public ResponseMessage<List<Lawsuit>> findByStatus(@PathVariable String lawsuitStatus) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.get",
				new Object[] { lawsuitStatus }), lawsuitService.findByStatus(lawsuitStatus));
	}

	/**
	 * 查看官司详细信息
	 * 
	 * @param lawsuitId
	 * @return
	 */
	@GetMapping("/{lawsuitId}")
	public ResponseMessage<Lawsuit> findByCaseId(@PathVariable String lawsuitId) {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] { lawsuitId }),
				lawsuitService.findByLawsuitId(lawsuitId));
	}

	/**
	 * 查找所有官司
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseMessage<List<Lawsuit>> query() {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] {}),
				lawsuitService.findAll());
	}

	/**
	 * 更新官司信息
	 * 
	 * @param lawsuitId
	 * @param lawsuit
	 * @return
	 */
	@PutMapping("/{lawsuitId}")
	public ResponseMessage<Lawsuit> update(@PathVariable String lawsuitId, @Valid @RequestBody Lawsuit lawsuit) {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.case.get", new Object[] { lawsuitId }),
				lawsuitService.update(lawsuitId, lawsuit));
	}
}
