package com.fxs.platform.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *纷争信息领域模型 
 *
 */
@Entity
public class DisputeInfo {

	@Id
	private String id;

	private String description;

	private String status;

	private Date createAt;

	@Transient
	private List<String> question;

	@Transient
	private List<String> answer;
	
	@Transient
	private String isRootQuestion;
	
	@Transient
	private String questionType;
	
	public DisputeInfo() {
	}

	public DisputeInfo(String id, String description, String status, Date createAt) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.createAt = createAt;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getQuestion() {
		return question;
	}

	public void setQuestion(List<String> question) {
		this.question = question;
	}

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getIsRootQuestion() {
		return isRootQuestion;
	}

	public void setIsRootQuestion(String isRootQuestion) {
		this.isRootQuestion = isRootQuestion;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
}
