package com.fxs.platform.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *问题领域模型 
 *
 */
@Entity
public class Question {

	@Id
	private String id;

	private String description;

	private String isRootQuestion;
	
	private String questionType;

	private String belongsToFalltypus;
	
	@Transient
	private List<String> answers;

	@ManyToOne
	@JoinColumn(name = "cases_id")
	private Cases cases;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public String getIsRootQuestion() {
		return isRootQuestion;
	}

	public void setIsRootQuestion(String isRootQuestion) {
		this.isRootQuestion = isRootQuestion;
	}

	public Cases getCases() {
		return cases;
	}

	public void setCases(Cases cases) {
		this.cases = cases;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	public String getBelongsToFalltypus() {
		return belongsToFalltypus;
	}

	public void setBelongsToFalltypus(String belongsToFalltypus) {
		this.belongsToFalltypus = belongsToFalltypus;
	}
}
