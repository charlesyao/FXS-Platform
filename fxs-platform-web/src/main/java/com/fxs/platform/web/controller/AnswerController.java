package com.fxs.platform.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.service.AnswerService;

@Controller
@RequestMapping("/admin/answer")
public class AnswerController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	private AnswerService answerService;
	
	@Autowired
	HttpSession httpSession;


	/**
	 * 更新答案问题关联信息
	 * 
	 * @param caseId
	 * @param cases
	 * @return
	 */
	@PutMapping
	@ResponseBody
	public void update(@Valid @RequestBody Answer answer) {
		answerService.updateNextQuestion(answer.getNextQuestionId(), answer.getId());
	}
}
