package com.fxs.platform.service.impl;

import java.util.List;

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
	public Question getByQuestionId(int qId) {
		
		return questionRepository.findOne(qId);
	}

	@Override
	public List<Question> getAllQuestion() {
		return questionRepository.findAll();
	}

	@Override
	public Question save(Question question) {
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
	public void delete(int qId) {
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

}
