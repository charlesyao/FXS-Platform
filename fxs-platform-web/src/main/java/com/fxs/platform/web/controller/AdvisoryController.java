package com.fxs.platform.web.controller;

import javax.validation.Valid;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.domain.Advisory;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseCodeType;
import com.fxs.platform.security.core.support.SimpleResponse;
import com.fxs.platform.service.AdvisoryService;

@RestController
@RequestMapping("/advisory")
public class AdvisoryController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	AdvisoryService advisoryService;

	@PostMapping("/free")
	public SimpleResponse<Advisory> advisory(@Valid @RequestBody Advisory advisory) {
		return new SimpleResponse<Advisory>(ResponseCodeType.ZERO.getValue(),
				localeMessageSourceService.getMessage("fxs.platform.application.advisory"),
				advisoryService.create(advisory));
	}

	@PostMapping("/phone")
	public SimpleResponse<Null> advisory(String phone) {
		return new SimpleResponse<Null>(ResponseCodeType.ZERO.getValue(),
				localeMessageSourceService.getMessage("fxs.platform.application.advisory"),
				ObjectUtils.NULL);
	}
}
