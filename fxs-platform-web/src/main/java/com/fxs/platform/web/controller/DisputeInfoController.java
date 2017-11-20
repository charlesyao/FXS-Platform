package com.fxs.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.DisputeInfo;
import com.fxs.platform.domain.Question;
import com.fxs.platform.service.AnswerService;
import com.fxs.platform.service.DisputeInfoService;
import com.fxs.platform.service.QuestionService;

@Controller
@RequestMapping("/disputeInfo")
public class DisputeInfoController {
	@Autowired
	private DisputeInfoService disputeInfoService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private QuestionService questionService;


	@GetMapping("/getAllDisputeInfo")
	public String getAllDisputeInfo(ModelMap map) {
		map.addAttribute("questionList", questionService.getAllQuestion());

		return "public_list_dispute_info";
	}

	@GetMapping(value = "/createDisputeInfo")
	public String newDisputeInfoForm(@ModelAttribute(value = "disputeInfo") DisputeInfo disputeInfo,
			BindingResult bindingResult, ModelMap map) {
		
		return "public_add_dispute_info";
	}

	@PostMapping(value = "/createDisputeInfo")
	public String create(DisputeInfo disputeInfo, BindingResult result,
			SessionStatus status) {
		disputeInfoService.save(disputeInfo);

		List<String> questions = disputeInfo.getQuestion();

		Question ques = null;

		if (questions != null) {
			for (String question : questions) {
				ques = new Question();
				ques.setDescription(question);
				ques.setIsRootQuestion(disputeInfo.getIsRootQuestion());
				ques.setDisputeInfo(disputeInfo);

				questionService.save(ques);

				List<String> answers = disputeInfo.getAnswer();
				Answer answer = null;

				if (answers != null) {
					for (int i = 0; i < answers.size(); i++) {
						answer = new Answer();
						answer.setDescription(answers.get(i));
						answer.setQuestion(ques);
						answerService.save(answer);
					}
				}

			}
		}

		status.setComplete();
		return "redirect:/disputeInfo/getAllDisputeInfo";
	}

	@GetMapping(value = "/viewDisputeInfo/{id}")
	public String view(@PathVariable("id") int id, ModelMap map) {
		
		Question question = questionService.getByQuestionId(id);
		map.addAttribute("availableQuestions", questionService.getAllQuestion());
		map.addAttribute("question", question);
		
		return "public_view_dispute_info";
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
}