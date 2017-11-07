package com.fxs.platform.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Questionnaire;

@Repository
public interface QuestionnaireRepository extends FxsRepository<Questionnaire> {
	List<Questionnaire> findByQId(String qId);
}
