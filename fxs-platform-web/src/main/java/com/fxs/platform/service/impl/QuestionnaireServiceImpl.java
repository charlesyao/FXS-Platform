package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.Questionnaire;
import com.fxs.platform.repository.QuestionnaireRepository;
import com.fxs.platform.service.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	QuestionnaireRepository questionnaireRepository;
	
	@Override
	@Cacheable(value="questionnaire", key="#qId")
	
	public List<Questionnaire> findByQId(String qId) {
		return questionnaireRepository.findByParentId(qId);
	}

	@Override
	public Questionnaire create(Questionnaire questionnaire) {
		return questionnaireRepository.save(questionnaire);
	}
}
