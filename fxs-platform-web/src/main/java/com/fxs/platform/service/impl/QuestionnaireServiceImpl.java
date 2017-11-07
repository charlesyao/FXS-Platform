package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.Questionnaire;
import com.fxs.platform.service.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Override
	@Cacheable(value="questionnaire", key="#qId")
	
	public List<Questionnaire> findByQId(String qId) {
		return null;
	}
}
