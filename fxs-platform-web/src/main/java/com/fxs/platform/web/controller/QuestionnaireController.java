package com.fxs.platform.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxs.platform.domain.Questionnaire;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.FalltypusService;
import com.fxs.platform.service.QuestionnaireService;

@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	QuestionnaireService questionnaireService;
	
	@Autowired
	FalltypusService falltypusService;
	
	@PostMapping
	@ResponseBody
	public ResponseMessage<Questionnaire> create(@Valid @RequestBody Questionnaire questionnaire) {
		return Result.success(questionnaireService.create(questionnaire));
	}
}
