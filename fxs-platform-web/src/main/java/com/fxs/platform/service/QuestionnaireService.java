package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Questionnaire;

public interface QuestionnaireService {
	
	Questionnaire create(Questionnaire questionnaire);
	
	public List<Questionnaire> findByQId(String qId);
	
}
