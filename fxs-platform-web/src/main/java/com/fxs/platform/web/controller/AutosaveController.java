package com.fxs.platform.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
@RequestMapping("/autosave")
public class AutosaveController {
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_QUESTIONNAIRE";

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@GetMapping("/{id}")
	public void save(@PathVariable String id, ServletWebRequest request) {
		String qId = this.get(request);
		
		if(StringUtils.isNotBlank(qId)) {
			sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX, qId.concat(id));
		} else {
			sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX, id);
		}
	}

	public String get(ServletWebRequest request) {
		return sessionStrategy.getAttribute(request, SESSION_KEY_PREFIX) == null ? "" : sessionStrategy.getAttribute(request, SESSION_KEY_PREFIX).toString();
	}

	public void remove(ServletWebRequest request) {
		sessionStrategy.removeAttribute(request, SESSION_KEY_PREFIX);
	}
}
