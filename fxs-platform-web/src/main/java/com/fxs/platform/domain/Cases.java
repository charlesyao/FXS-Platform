package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cases {
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
	private String id;

	/**
	 * 主类型：法律咨询或者打官司
	 */
	private CaseType caseTypeLevelOne;

	/**
	 * 子类型： 免费咨询/找律师打官司/电话咨询
	 */
	private CaseType caseTypeLevelTwo;

	/**
	 * 案件当前状态
	 * 
	 * @see com.fxs.platform.utils.CaseStatus
	 */
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CaseType getCaseTypeLevelOne() {
		return caseTypeLevelOne;
	}

	public void setCaseTypeLevelOne(CaseType caseTypeLevelOne) {
		this.caseTypeLevelOne = caseTypeLevelOne;
	}

	public CaseType getCaseTypeLevelTwo() {
		return caseTypeLevelTwo;
	}

	public void setCaseTypeLevelTwo(CaseType caseTypeLevelTwo) {
		this.caseTypeLevelTwo = caseTypeLevelTwo;
	}

	public String getStatus() {
		return status;
	}

}
