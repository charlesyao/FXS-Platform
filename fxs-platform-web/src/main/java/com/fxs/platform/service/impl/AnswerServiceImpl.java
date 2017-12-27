package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.repository.AnswerRepository;
import com.fxs.platform.repository.QuestionRepository;
import com.fxs.platform.service.AnswerService;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Answer getByAnswerId(int answerId) {
		return answerRepository.findOne(answerId);
	}

	@Override
	public List<Answer> getAllAnswer() {
		
		return answerRepository.findAll();
	}

	@Override
	public Answer save(Answer answer) {
		return answerRepository.save(answer);
	}

	@Override
	public void update(Answer answer) {
		save(answer);
	}

	@Override
	public void view(Answer answer) {
		save(answer);

	}

	@Override
	public void delete(int aId) {
		Answer s = getByAnswerId(aId);
		answerRepository.delete(s);
	}

	@Override
	public List<Answer> getAllAnswerByQuestionId(int qId) {
		return answerRepository.getAllAnswerByQuestionId(qId);
	}

	@Override
	public void updateNextQuestion(Answer answer) {
		// TODO Auto-generated method stub
		answerRepository.updateNextQuestion(answer.getNextQuestionId(), answer.getId());
	}
}
