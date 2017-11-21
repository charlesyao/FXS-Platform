package com.fxs.platform.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.Question;
import com.fxs.platform.dto.QuestionDto;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.AnswerService;
import com.fxs.platform.service.QuestionService;

@Controller
@RequestMapping("/public/question")
public class QuestionController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;

	/**
	 * 获取根问题
	 * 
	 * @return
	 */
	@GetMapping("/root")
	public ResponseMessage<Question> getRootQuestion() {
		return Result.success(questionService.findRootQuestion());
	}

	/**
	 * 根据问卷选择的答案加载下一个问题
	 * 
	 * @param qId
	 * @return
	 */
	@GetMapping("/{qId}")
	@ResponseBody
	public ResponseMessage<QuestionDto> getNextQuestion(@PathVariable int qId, ModelMap map) {
		
		QuestionDto qDto = new QuestionDto();
		
		Question question = questionService.getByQuestionId(qId);
		List<Answer> answerList = answerService.getAllAnswerByQuestionId(question.getId());
		
		
		qDto.setQuestion(question);
		qDto.setAnswers(answerList);
		
		map.addAttribute("questionItem", qDto);
		
		return Result.success(qDto);
	}
}
