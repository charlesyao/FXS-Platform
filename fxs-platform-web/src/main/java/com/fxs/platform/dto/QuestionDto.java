package com.fxs.platform.dto;

import java.util.List;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.Question;

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
