package com.fxs.platform.web.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fxs.platform.domain.Questionnaire;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.QuestionnaireService;

@RestController
@RequestMapping("/autosave")
public class AutosaveController {
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_QUESTIONNAIRE";
	
	private static final String STEP1_PREFIX = "1";
	private static final String STEP2_PREFIX = "2";
	private static final String STEP3_PREFIX = "3";

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@GetMapping("/{id}")
	public ResponseMessage<List<Questionnaire>> save(@PathVariable String id, ServletWebRequest request) {
		String qId = this.get(request);
		
		if(StringUtils.isNotBlank(qId)) {
			String[] ids = splitStringEvery(qId, 2);
			String qIds = "";

			if (id.startsWith(STEP1_PREFIX)) {
				ids[0] = id;
				qIds = String.join("", ids);
			} else if(id.startsWith(STEP2_PREFIX) && ids.length == 2) {
				ids[1] = id;
				qIds = String.join("", ids);
			} else if (id.startsWith(STEP3_PREFIX) && ids.length == 3) {
				ids[2] = id;
				qIds = String.join("", ids);
			} else {
				qIds = qId.concat(id);
			}
			
			sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX, qIds);
			
			if (qIds.length() == 6) {
				return Result.success(questionnaireService.findByQId(qIds));
			}
		} else {
			sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX, id);
		}
		
		return Result.success(null);
	}

	public String get(ServletWebRequest request) {
		return sessionStrategy.getAttribute(request, SESSION_KEY_PREFIX) == null ? "" : sessionStrategy.getAttribute(request, SESSION_KEY_PREFIX).toString();
	}

	public void remove(ServletWebRequest request) {
		sessionStrategy.removeAttribute(request, SESSION_KEY_PREFIX);
	}
	
	private String[] splitStringEvery(String s, int interval) {
	    int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
	    String[] result = new String[arrayLength];

	    int j = 0;
	    int lastIndex = result.length - 1;
	    for (int i = 0; i < lastIndex; i++) {
	        result[i] = s.substring(j, j + interval);
	        j += interval;
	    } //Add the last bit
	    result[lastIndex] = s.substring(j);

	    return result;
	}
}
