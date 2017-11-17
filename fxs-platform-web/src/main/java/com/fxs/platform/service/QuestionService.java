package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Question;

public interface QuestionService {
	Question getByQuestionId(int qId);

	List<Question> getAllQuestion();

	Question save(Question question);

	void update(Question question);

	void view(Question question);

	void delete(int qId);
	
	Question findRootQuestion();
}
