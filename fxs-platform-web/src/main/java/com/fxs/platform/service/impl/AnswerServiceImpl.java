package com.fxs.platform.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.repository.AnswerRepository;
import com.fxs.platform.service.AnswerService;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Override
	public Answer getByAnswerId(String answerId) {
		return answerRepository.findById(answerId);
	}

	@Override
	public List<Answer> getAllAnswer() {
		
		return answerRepository.findAll();
	}

	@Override
	public Answer save(Answer answer) {
		answer.setId(String.valueOf(new Random().nextInt(99999999)));
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
	public void delete(String aId) {
		Answer answer = getByAnswerId(aId);
		
		if(!ObjectUtils.isEmpty(answer)) {
			answerRepository.delete(answer);
		}
	}

	@Override
	public List<Answer> getAllAnswerByQuestionId(String qId) {
		return answerRepository.getAllAnswerByQuestionId(qId);
	}

	@Override
	public void updateNextQuestion(Answer answer) {
		// TODO Auto-generated method stub
		answerRepository.updateNextQuestion(answer.getNextQuestionId(), answer.getId());
	}
}
