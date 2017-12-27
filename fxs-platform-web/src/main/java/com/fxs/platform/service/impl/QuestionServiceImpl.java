package com.fxs.platform.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxs.platform.domain.Question;
import com.fxs.platform.repository.QuestionRepository;
import com.fxs.platform.service.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question getByQuestionId(String qId) {
		
		return questionRepository.findById(qId);
	}

	@Override
	public List<Question> getAllQuestion() {
		return questionRepository.findAll();
	}
	
	@Override
	public List<Question> getAllAvailableQuestion() {
		return questionRepository.getAllAvailableQuestion();
	}

	@Override
	public Question save(Question question) {
		question.setId(String.valueOf(new Random().nextInt(99999999)));
		return questionRepository.save(question);
	}

	@Override
	public void update(Question question) {
		save(question);
	}

	@Override
	public void view(Question question) {
		save(question);
	}

	@Override
	public void delete(String qId) {
		Question question = getByQuestionId(qId);
		questionRepository.delete(question);
	}

	@Override
	public Question findRootQuestion() {
		return questionRepository.findRootQuestion();
	}

	@Override
	public List<Question> findOptionalQuestions() {
		return questionRepository.findOptionalQuestions();
	}

	@Override
	public void updateQFMapping(Question question) {
		// TODO Auto-generated method stub
		questionRepository.updateQuestion(question.getBelongsToFalltypus(), question.getId());
	}

	@Override
	public List<Question> findQuestionsByFalltypus(String fId) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByFalltypus(fId);
	}

}
