package com.fxs.platform.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fxs.platform.utils.DateUtil;

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
	
	private String createAt = DateUtil.getCurrentDate();
	
	private String updateAt = DateUtil.getCurrentDate();
	
	@Transient
	private List<String> answers;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public String getCreateAt() {
		return createAt;
	}


	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}


	public String getUpdateAt() {
		return updateAt;
	}


	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
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
