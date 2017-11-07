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

import com.fxs.platform.domain.Lawsuit;
import com.fxs.platform.dto.LawsuitDto;
import com.fxs.platform.repository.condition.LawsuitCondition;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.LawsuitService;

/**
 * 打官司接口
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
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.save.success"),
				lawsuitService.create(lawsuit));
	}

	/**
	 * 分页查找官司信息
	 * 
	 * @see com.fxs.platform.utils.LawsuitType
	 * @see com.fxs.platform.utils.CaseStatus
	 * 
	 * @param type
	 * @return
	 */
	@GetMapping
	public ResponseMessage<Page<LawsuitDto>> query(LawsuitCondition condition, Pageable pageable) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.get.success"),
				lawsuitService.query(condition, pageable));
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
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.get.success",
				new Object[] { lawsuitId }), lawsuitService.update(lawsuitId, lawsuit));
	}
}
