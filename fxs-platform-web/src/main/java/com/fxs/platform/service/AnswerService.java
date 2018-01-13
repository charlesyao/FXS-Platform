package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.Question;
import com.fxs.platform.repository.condition.AnswerCondition;

public interface AnswerService {
	Answer getByAnswerId(String answerId);

	List<Answer> getAllAnswer();
	
	List<Answer> getAllAnswerByQuestionId(String qId);

	Answer save(Answer answer);

	void update(Answer answer);

	void view(Answer answer);

	void delete(String answerId);
	
	void updateNextQuestion(Answer answer);
	
	Answer query(AnswerCondition condition);
}
