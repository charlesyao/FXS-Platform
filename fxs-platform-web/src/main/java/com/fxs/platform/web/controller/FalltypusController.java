package com.fxs.platform.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.Falltypus;
import com.fxs.platform.domain.Question;
import com.fxs.platform.dto.FalltypusDto;
import com.fxs.platform.dto.QuestionDto;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.AnswerService;
import com.fxs.platform.service.FalltypusService;
import com.fxs.platform.service.QuestionService;
import com.fxs.platform.utils.SystemConstants;

/**
 * 案件类型控制器类
 *
 */
@Controller
@RequestMapping("/public/falltypus")
public class FalltypusController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	FalltypusService falltypusService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;
	
	@Autowired
	HttpSession session;

	@PostMapping
	@ResponseBody
	public ResponseMessage<Falltypus> create(@Valid @RequestBody Falltypus falltypus) {
		return Result.success(falltypusService.create(falltypus));
	}

	/**
	 * 获取案件类型
	 * 
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public ResponseMessage<List<FalltypusDto>> getFirstLevelFalltypus() {
		return Result.success(falltypusService.findFirstLevelFalltypus());
	}

	/**
	 * 获取案件子类型
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{level}/{caseType}/{id}")
	public String getSubFalltypusForConsultation(
					@PathVariable String level, 
					@PathVariable String caseType, 
					@PathVariable String id, 
					ModelMap map,
					@ModelAttribute("question") Question question) {
		
		if (level.equals("level1")) {
			
			session.setAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE, id);
		} else if (level.equals("level2")) {
			
			session.setAttribute(SystemConstants.FALLTYPUS_LEVEL2_TYPE, id);
			
			//查询当前案件类型下属的问题
			//session.setAttribute("nextAllQuestions", questionService.findQuestionsByFalltypus(id));
		}
		
		
		String target = "";
		List<FalltypusDto> subFalltypusList = falltypusService.findSubFalltypusByParentId(id);

		if (subFalltypusList.size() == 0) {// 判断没有子类型
			//初始化默认QA列表
			session.setAttribute(SystemConstants.QA_MAP, new HashMap<Integer, Object[]>());
			QuestionDto qDto = new QuestionDto();
			List<Answer> answerList = new ArrayList<Answer>();
			
			//查找与当前案件类型关联的跟问题
			Question rootQuestion = questionService.findCurrentRootQuestion(id);
			
			if (! ObjectUtils.isEmpty(rootQuestion)) {
				answerList = answerService.getAllAnswerByQuestionId(rootQuestion.getId());
			}
			
			qDto.setQuestion(rootQuestion);
			qDto.setAnswers(answerList);
			
			map.addAttribute("question", qDto);
			
			//获取当前案件类型下属的root optional question
			Question optionalRootQuestion = questionService.findQuestionsByFalltypus(id);
			
			if (!ObjectUtils.isEmpty(optionalRootQuestion)) {
				QuestionDto questionDto = new QuestionDto();
				List<Answer> optionalAnswerList = answerService.getAllAnswerByQuestionId(optionalRootQuestion.getId());
				
				questionDto.setQuestion(optionalRootQuestion);
				questionDto.setAnswers(optionalAnswerList);
				
				session.setAttribute("optionalQuestion", questionDto);
			}

			if (caseType.equals("consulting")) {// 咨询跳转路由

				target = "public_consulting_free_step2";
			} else if (caseType.equals("lawsuit")) {// 打官司跳转路由
				target = "public_lawsuit_lawyer_step3";
			}

		} else {// 有案件子类型
			map.addAttribute("subFalltypusList", subFalltypusList);

			if (caseType.equals("consulting")) {// 咨询跳转路由
				target = "public_consulting_free_step1";
			} else if (caseType.equals("lawsuit")) {// 打官司跳转路由
				target = "public_lawsuit_lawyer_step2";
			}

		}

		return target;
	}
}
