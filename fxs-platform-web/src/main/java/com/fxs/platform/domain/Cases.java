package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
public class Cases {
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
	private String id;

	/**
	 * 一级类型
	 */
	//private CaseType caseTypeLevelOne;

	/**
	 * 二级类型
	 */
	//private CaseType caseTypeLevelTwo;

	private Set<DisputeInfo> disputeInfo;

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

	/*public CaseType getCaseTypeLevelOne() {
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
	}*/

	public String getStatus() {
		return status;
	}

    public Set<DisputeInfo> getDisputeInfo() {
        return disputeInfo;
    }

    public void setDisputeInfo(Set<DisputeInfo> disputeInfo) {
        this.disputeInfo = disputeInfo;
    }
}
