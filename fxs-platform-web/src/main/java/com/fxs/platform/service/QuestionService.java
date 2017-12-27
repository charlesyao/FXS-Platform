package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.Question;

public interface QuestionService {
	Question getByQuestionId(int qId);

	List<Question> getAllQuestion();
	
	List<Question> getAllAvailableQuestion();

	Question save(Question question);

	void update(Question question);

	void view(Question question);

	void delete(int qId);
	
	Question findRootQuestion();
	
	List<Question> findOptionalQuestions();
	
	void updateQFMapping(Question question);
}
