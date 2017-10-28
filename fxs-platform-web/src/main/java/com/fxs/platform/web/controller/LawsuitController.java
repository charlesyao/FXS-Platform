package com.fxs.platform.web.controller;

import javax.validation.Valid;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.domain.Lawsuit;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseCodeType;
import com.fxs.platform.security.core.support.SimpleResponse;
import com.fxs.platform.service.LawsuitService;

@RestController
@RequestMapping("/lawsuit")
public class LawsuitController {
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	LawsuitService lawsuitService;

	@PostMapping("/lawer")
	public SimpleResponse<Lawsuit> advisory(@Valid @RequestBody Lawsuit lawsuit) {
		return new SimpleResponse<Lawsuit>(ResponseCodeType.ZERO.getValue(),
				localeMessageSourceService.getMessage("fxs.platform.application.advisory"),
				lawsuitService.create(lawsuit));
	}

	@PostMapping("/self")
	public SimpleResponse<Null> advisory(String phone) {
		return new SimpleResponse<Null>(ResponseCodeType.ZERO.getValue(),
				localeMessageSourceService.getMessage("fxs.platform.application.advisory"), ObjectUtils.NULL);
	}
}
