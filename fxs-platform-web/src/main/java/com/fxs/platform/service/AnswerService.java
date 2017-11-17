package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Answer;

public interface AnswerService {
	Answer getByAnswerId(int answerId);

	List<Answer> getAllAnswer();
	
	List<Answer> getAllAnswerByQuestionId(int qId);

	Answer save(Answer answer);

	void update(Answer answer);

	void view(Answer answer);

	void delete(int answerId);
	
	Answer updateNextQuestion(int answerId, int nextQuestionId);
}
