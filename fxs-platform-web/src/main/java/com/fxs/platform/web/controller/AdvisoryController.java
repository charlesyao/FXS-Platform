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

import com.fxs.platform.domain.Advisory;
import com.fxs.platform.dto.AdvisoryDto;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseCodeType;
import com.fxs.platform.security.core.support.SimpleResponse;
import com.fxs.platform.service.AdvisoryService;

/**
 * 法律咨询接口
 * 
 * @author Charles
 *
 */
@RestController
@RequestMapping("/advisory")
public class AdvisoryController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	AdvisoryService advisoryService;

	/**
	 * 提交法律咨询信息
	 * 
	 * @param advisory
	 * @return
	 */
	@PostMapping
	public SimpleResponse<Advisory> advisory(@Valid @RequestBody Advisory advisory) {
		return new SimpleResponse<Advisory>(ResponseCodeType.ZERO.getValue(),
				localeMessageSourceService.getMessage("fxs.platform.application.advisory.save"),
				advisoryService.create(advisory));
	}

	/**
	 * 根据法律咨询类型获取 type: 免费/电话
	 * 
	 * @param type
	 * @return
	 */
	@GetMapping("/{type}")
	public SimpleResponse<List<AdvisoryDto>> query(@PathVariable String type) {

		return new SimpleResponse<List<AdvisoryDto>>(ResponseCodeType.ZERO.getValue(),
				localeMessageSourceService.getMessage("fxs.platform.application.advisory.get", new Object[] { type }),
				advisoryService.query(type));
	}
}
