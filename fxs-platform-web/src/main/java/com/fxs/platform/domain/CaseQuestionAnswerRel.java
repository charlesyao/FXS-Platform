package com.fxs.platform.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fxs.platform.utils.DateUtil;

/**
 * 案件-问题-答案 对应关系表
 *
 */
@Entity
public class CaseQuestionAnswerRel implements Serializable, Comparable<CaseQuestionAnswerRel> {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String caseId;

	private String questionId;

	private String questionType;

	private String questionDesc;

	private String answerId;

	private String answerDesc;

	@Transient
	private List<Answer> answers;
	
	private LocalDateTime createAt = LocalDateTime.now();

	private LocalDateTime updateAt = LocalDateTime.now();

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getAnswerDesc() {
		return answerDesc;
	}

	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}

	public int compareTo(CaseQuestionAnswerRel arg) {
		if ("1".equals(arg.getQuestionType())) {
			return 1;
		} else {
			return 0;
		}
	}
}
