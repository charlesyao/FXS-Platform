package com.fxs.platform.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.CasesService;

/**
 * 
 * @author Charles
 *
 */
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
	 * @param advisory
	 * @return
	 */
	@PostMapping
	public ResponseMessage<Cases> create(@Valid @RequestBody Cases cases) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.advisory.save"),
				casesService.create(cases));
	}

	/**
	 * 根据法律咨询类型获取 type: 免费/电话
	 * 
	 * @param type
	 * @return
	 */
	@GetMapping("/{type}/{subType}")
	public ResponseMessage<List<CasesDto>> advisoryQuery(@PathVariable String type, @PathVariable String subType) {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.advisory.get", new Object[] { type }),
				casesService.query(type, subType));
	}
}
