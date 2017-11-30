package com.fxs.platform.domain;

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

	private String description;
	
	private String addAfterConfirm;
	
	private String noAnswer;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
	private int nextQuestionId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getAddAfterConfirm() {
		return addAfterConfirm;
	}

	public void setAddAfterConfirm(String addAfterConfirm) {
		this.addAfterConfirm = addAfterConfirm;
	}

	public String getNoAnswer() {
		return noAnswer;
	}

	public void setNoAnswer(String noAnswer) {
		this.noAnswer = noAnswer;
	}
}
