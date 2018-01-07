package com.fxs.platform.web.controller;

import java.util.List;

import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.DisputeInfo;
import com.fxs.platform.domain.Question;
import com.fxs.platform.service.AnswerService;
import com.fxs.platform.service.FalltypusService;
import com.fxs.platform.service.QuestionService;
import com.fxs.platform.utils.ResponseCodeEnum;


@Controller
@RequestMapping("/disputeInfo")
public class DisputeInfoController {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	FalltypusService falltypusService;
	/**
	 * 获取所有的纷争信息
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping("/getAllDisputeInfo")
	public String getAllDisputeInfo(ModelMap map) {
		map.addAttribute("questionList", questionService.getAllQuestion());
		map.addAttribute("availableFalltypus", falltypusService.findAll());
		return "public_list_dispute_info";
	}
	
	/**
	 * 根据案件类型过滤已绑定的问题
	 * 
	 * @param filter
	 * @param map
	 * @return
	 */
	@GetMapping("/filterDisputeInfo/{filter}")
	public String getDisputeInfoWithFilter(@PathVariable("filter") String filter, ModelMap map) {
		
		if (! ObjectUtils.isEmpty(filter) && !filter.equals("-1")) {
			map.addAttribute("questionList", questionService.filterAllQuestionsByFalltypus(filter));
		} else {
			map.addAttribute("questionList", questionService.getAllQuestion());
		}
		
		map.addAttribute("availableFalltypus", falltypusService.findAll());
		return "public_list_dispute_info :: questionBlock-fragment";
	}

	/**
	 * 创建问题
	 * @param disputeInfo
	 * @param bindingResult
	 * @param map
	 * @return
	 */
	@GetMapping(value = "/createDisputeInfo")
	public String newDisputeInfoForm(@ModelAttribute(value = "disputeInfo") DisputeInfo disputeInfo,
			BindingResult bindingResult, ModelMap map) {
		
		return "public_add_dispute_info";
	}

	@PostMapping(value = "/createDisputeInfo")
	public String create(DisputeInfo disputeInfo, BindingResult result,
			SessionStatus status) {
		
		List<String> questions = disputeInfo.getQuestion();

		Question ques = null;

		if (!ObjectUtils.isEmpty(questions)) {
			for (String question : questions) {
				ques = new Question();
				ques.setDescription(question);
				ques.setIsRootQuestion(disputeInfo.getIsRootQuestion());
				ques.setQuestionType(disputeInfo.getQuestionType());
				//ques.setDisputeInfo(disputeInfo);

				questionService.save(ques);

				List<String> answers = disputeInfo.getAnswer();
				Answer answer = null;

				if (answers != null) {
					for (int i = 0; i < answers.size(); i++) {
						
						if(!ObjectUtils.isEmpty(answers.get(i))) {
							answer = new Answer();
							answer.setDescription(answers.get(i));
							answer.setQuestion(ques);
							answerService.save(answer);
						}
					}
				}
			}
		}

		return "redirect:/disputeInfo/getAllDisputeInfo";
	}

	@GetMapping(value = "/viewDisputeInfo/{id}")
	public String view(@PathVariable("id") String id, ModelMap map) {
		
		Question question = questionService.getByQuestionId(id);
		
		map.addAttribute("availableFalltypus", falltypusService.findAll());
		map.addAttribute("availableQuestions", questionService.getAllQuestion());
		
		if(!ObjectUtils.isEmpty(question)) {
			map.addAttribute("mappedQuestionAnswers", answerService.getAllAnswerByQuestionId(question.getId()));
			map.addAttribute("question", question);
		} else {
			map.addAttribute("mappedQuestionAnswers", null);
			map.addAttribute("question", null);
		}
		
		
		
		return "public_view_dispute_info";
	}

	@DeleteMapping(value = "/question/delete/{id}")
    @ResponseBody
	public ResponseMessage<String> deleteQuestion(@PathVariable("id") String id) {

		questionService.delete(id);
        return Result.success("success");
	}

	@DeleteMapping(value = "/answer/delete/{id}")
	@ResponseBody
	public ResponseMessage<String> deleteAnswer(@PathVariable("id") String id) {

		answerService.delete(id);
		return Result.success("success");
	}
	
	@PostMapping(value = "/updateAnswer")
	public String update(@ModelAttribute(value = "answer") Answer answer, BindingResult result,
			SessionStatus status) {
		//List<Integer> nextQuestionIds = question.getDisputeInfo().getNextQuestion();

		//List<String> answers = question.getDisputeInfo().getAnswer();

		/*if (answers != null) {
			for (int i = 0; i < answers.size(); i++) {
				//answerService.updateNextQuestion(answers.get(i));
			}
		}*/

		status.setComplete();
		return "redirect:/disputeInfo/getAllDisputeInfo";
	}
	
	/**
	 * 更新问题信息
	 * 
	 * @param question
	 * @return
	 */
	@PutMapping(value = "/update/question/basic")
	@ResponseBody
	public ResponseMessage<String> updateQuestionBasicInfo(@RequestBody Question question) {
		
		Question qInfo = questionService.getByQuestionId(question.getId());
		
		Question rootQuestion = null;
		
		//在编辑问题的时候，如果将当前问题设置为根问题，则需要判断当前选择的带绑定的案件类型下是否已经有绑定根问题，如有则不能重复绑定
		if ("Y".equals(question.getIsRootQuestion())) {
			rootQuestion = questionService.findCurrentRootQuestion(question.getBelongsToFalltypus());
		}
		
		if(!ObjectUtils.isEmpty(rootQuestion) && !rootQuestion.getBelongsToFalltypus().equals(qInfo.getBelongsToFalltypus())) {
			return Result.error(String.valueOf(ResponseCodeEnum.ERROR.getCode()), "同一个案件类型不能绑定多个根问题");
		}
		
		
		if(!ObjectUtils.isEmpty(qInfo)) {
			
			//如果当前选择的待绑定的案件类型与原数据不一致，则重新绑定
			if(! question.getBelongsToFalltypus().equals(qInfo.getBelongsToFalltypus())) {
				questionService.updateQFMapping(question);
			}
			
			//更新问题的基本信息
			questionService.update(question);
		}

		return Result.success("success");
	}
	
	/**
	 * 解除问题和案件类型的绑定关系
	 * 
	 * @param question
	 * @return
	 */
	@PutMapping(value = "/update/question/qfMapping")
	@ResponseBody
	public ResponseMessage<String> updateQuestionFalltypusMapping(@RequestBody Question question) {
		Question qInfo = questionService.getByQuestionId(question.getId());
		
		if(!ObjectUtils.isEmpty(qInfo)) {
			
			//如果当前选择的待绑定的案件类型与原数据不一致，则重新绑定,
			if(!qInfo.getBelongsToFalltypus().equals(question.getBelongsToFalltypus())) {
				questionService.updateQFMapping(question);
			}
		}
		
		return Result.success("success");
	}
	
	
	/**
	 * 更新问题信息
	 * 
	 * @param answer
	 * @return
	 */
	@PutMapping(value = "/update/answer/basic")
	@ResponseBody
	public ResponseMessage<String> updateAnswerBasicInfo(@RequestBody Answer answer) {
		
		Answer answerInfo = answerService.getByAnswerId(answer.getId());
		
		if(!ObjectUtils.isEmpty(answerInfo)) {
			
			//如果当前选择的待绑定的案件类型与原数据不一致，则重新绑定,
			if(!answer.getNextQuestionId().equals(answerInfo.getNextQuestionId())) {
				answerService.updateNextQuestion(answer);
			}
			
			//更新问题的基本信息
			answer.getQuestion().setId(answerInfo.getQuestion().getId());
			answer.setOther(answerInfo.getOther());
			answerService.update(answer);
		}
		

		return Result.success("success");
	}
	
	/**
	 * 解除答案和案件类型的绑定关系
	 * 
	 * @param answer
	 * @return
	 */
	@PutMapping(value = "/update/answer/qaMapping")
	@ResponseBody
	public ResponseMessage<String> updateAnswerFalltypusMapping(@RequestBody Answer answer) {
		Answer answerInfo = answerService.getByAnswerId(answer.getId());
		
		if(!ObjectUtils.isEmpty(answerInfo)) {
			
			//如果当前选择的待绑定的案件类型与原数据不一致，则重新绑定,
			if(!answerInfo.getNextQuestionId().equals(answer.getNextQuestionId())) {
				answerService.updateNextQuestion(answer);
			}
		}
		
		return Result.success("success");
	}
}
