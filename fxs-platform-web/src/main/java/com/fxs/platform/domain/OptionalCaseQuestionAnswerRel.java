package com.fxs.platform.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 案件-问题-答案 对应关系表
 *
 */
@Entity
public class OptionalCaseQuestionAnswerRel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;

	private String caseId;

	private String questionId;

	private String questionDesc;

	private List<String> answerId;

	private List<String> answerDesc;
	
	private String addAfterConfirm;
	
	private String cannotAnswer;

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

	public List<String> getAnswerId() {
		return answerId;
	}

	public void setAnswerId(List<String> answerId) {
		this.answerId = answerId;
	}

	public List<String> getAnswerDesc() {
		return answerDesc;
	}

	public void setAnswerDesc(List<String> answerDesc) {
		this.answerDesc = answerDesc;
	}

	public String getAddAfterConfirm() {
		return addAfterConfirm;
	}

	public void setAddAfterConfirm(String addAfterConfirm) {
		this.addAfterConfirm = addAfterConfirm;
	}

	public String getCannotAnswer() {
		return cannotAnswer;
	}

	public void setCannotAnswer(String cannotAnswer) {
		this.cannotAnswer = cannotAnswer;
	}
}
