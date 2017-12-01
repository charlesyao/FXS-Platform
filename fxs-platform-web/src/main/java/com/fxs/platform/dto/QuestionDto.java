package com.fxs.platform.dto;

import java.util.List;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.Question;

/**
 *问题-答案对应关系的封装 
 *
 */
public class QuestionDto {

	private Question question;
	private List<Answer> answers;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
