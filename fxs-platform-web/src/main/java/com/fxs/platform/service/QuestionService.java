package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Question;

public interface QuestionService {
	Question getByQuestionId(String qId);

	List<Question> getAllQuestion();
	
	List<Question> getAllAvailableQuestion();

	Question save(Question question);

	void update(Question question);

	void view(Question question);

	void delete(String qId);
	
	Question findRootQuestion();
	
	List<Question> findOptionalQuestions();
	
	List<Question> findQuestionsByFalltypus(String fId);
	
	void updateQFMapping(Question question);
}
