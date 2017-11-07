package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Questionnaire;

public interface QuestionnaireService {
	
	public List<Questionnaire> findByQId(String qId);
	
}
