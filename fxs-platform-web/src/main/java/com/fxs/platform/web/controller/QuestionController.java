package com.fxs.platform.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fxs.platform.domain.Question;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.QuestionService;

@Controller
@RequestMapping("/public/question")
public class QuestionController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	private QuestionService questionService;

	/**
	 * 获取根问题
	 * 
	 * @return
	 */
	@GetMapping("/root")
	public ResponseMessage<Question> getRootQuestion() {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.save.success"),
				questionService.findRootQuestion());
	}

	/**
	 * 根据问卷选择的答案加载下一个问题
	 * 
	 * @param qIdBrenden 
	 * @return
	 */
	@GetMapping("/{qId}")
	public ResponseMessage<Question> getNextQuestion(@PathVariable int qId) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.case.save.success"),
				questionService.getByQuestionId(qId));
	}
}
