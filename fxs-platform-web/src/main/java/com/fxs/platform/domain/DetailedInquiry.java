package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *案件追加信息领域模型 
 *
 */
@Entity
public class DetailedInquiry {
	@Id
	private String id;

	private String firstComments;

	private String secondComments;

	private String thirdComments;

	private String caseId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstComments() {
		return firstComments;
	}

	public void setFirstComments(String firstComments) {
		this.firstComments = firstComments;
	}

	public String getSecondComments() {
		return secondComments;
	}

	public void setSecondComments(String secondComments) {
		this.secondComments = secondComments;
	}

	public String getThirdComments() {
		return thirdComments;
	}

	public void setThirdComments(String thirdComments) {
		this.thirdComments = thirdComments;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
}
