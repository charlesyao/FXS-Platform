package com.fxs.platform.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
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
import com.fxs.platform.utils.QuestionWrapper;
import com.fxs.platform.utils.SystemConstants;

@Controller
@RequestMapping("/public/question")
public class QuestionController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	HttpSession httpSession;

	/**
	 * 获取根问题
	 * 
	 * @return
	 */
	@GetMapping("/root")
	public ResponseMessage<Question> getRootQuestion() {
		Question question = questionService.findRootQuestion();
		
		return Result.success(question);
	}

	/**
	 * 根据问卷选择的答案加载下一个问题
	 * 
	 * @param qId
	 * @return
	 */
	@GetMapping("/answer/{answerId}")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResponseMessage<String> getNextQuestion(@PathVariable int answerId, ModelMap map) {
		
		Map<Integer, Object[]> mapping = (HashMap<Integer, Object[]>)httpSession.getAttribute(SystemConstants.QA_MAP);
		
		Object[] qaArray = new Object[2];
		
		QuestionDto qDto = new QuestionDto();
		
		String questionSection ="";
		String startDiv ="";
		String endDiv ="";
		String answerSection = "";
		String questionId = "";
		
		//取得当前选择的答案
		Answer currentAnswer = answerService.getByAnswerId(answerId);
		
		if (! ObjectUtils.isEmpty(currentAnswer)) {
			//取得当前的问题
			Question currentQuestion = currentAnswer.getQuestion();
			
			qaArray[0] = currentQuestion;
			qaArray[1] = currentAnswer;
			
			mapping.put(currentQuestion.getId(), qaArray);

			httpSession.setAttribute(SystemConstants.QA_MAP, mapping);
			
		}
		
		
		//取得当前答案对应的下一个问题的ID, 并根据此ID取得相应的问题
		Question nextQuestion = questionService.getByQuestionId(currentAnswer.getNextQuestionId());
		
		if (! ObjectUtils.isEmpty(nextQuestion)) {
			//取得问题所对应的所有的答案
			List<Answer> nextAnswers = answerService.getAllAnswerByQuestionId(nextQuestion.getId());
			
			qDto.setQuestion(nextQuestion);
			qDto.setAnswers(nextAnswers);
			
			//map.addAttribute("questionItem", qDto);
			
			questionSection = "<div id='question_" + qDto.getQuestion().getId() + "'><h3>" + qDto.getQuestion().getDescription() + "</h3>";
			startDiv = "<div class='row'>";
			endDiv = "</div></div>";
			questionId = String.valueOf(qDto.getQuestion().getId());
			
			for (int index = 0; index < qDto.getAnswers().size(); index ++ ) {
				String singleAnswer = "<ul class='col-md-2 col-sm-2' id='" + qDto.getAnswers().get(index).getId() + "'>" + 
									  "<li class='introcoupCell btnSquare'>" + 
									  "<a class='answerLink' href='' id='" + qDto.getAnswers().get(index).getId() + "'>" + qDto.getAnswers().get(index).getDescription() + "</a>" + 
									  "</li>" + 
									  "</ul>";
				answerSection += singleAnswer;
			}
		}
		
		return Result.success(questionId, questionSection + startDiv + answerSection + endDiv);
	}
}
