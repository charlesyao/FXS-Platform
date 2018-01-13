package com.fxs.platform.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fxs.platform.utils.DateUtil;

/**
 * 案件-问题-答案 对应关系表
 *
 */
@Entity
public class CaseQuestionAnswerRel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String caseId;

	private String questionId;

	private String questionDesc;

	private String answerId;

	private String answerDesc;

	private String createAt = DateUtil.getCurrentDate();
	
	private String updateAt = DateUtil.getCurrentDate();
	
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

}
