package com.fxs.platform.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Answer {

	@Id
	@SequenceGenerator(name = "seq_contacts", sequenceName = "seq_contacts")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_contacts")
	private int id;

	@Column
	private String answer;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
	private int nextQuestionId;

	public Answer() {

	}

	public Answer(int id, String answer, Question question, int nextQuestionId) {
		super();
		this.id = id;
		this.answer = answer;
		this.question = question;
		this.nextQuestionId = nextQuestionId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getNextQuestionId() {
		return nextQuestionId;
	}

	public void setNextQuestionId(int nextQuestionId) {
		this.nextQuestionId = nextQuestionId;
	}
}
