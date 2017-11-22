package com.fxs.platform.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fxs.platform.utils.CaseStatus;

/**
 * 案件领域模型 免费法律咨询、打官司
 *
 */
@Entity
public class Cases {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;

	/**
	 * @see com.fxs.platform.utils.CaseType
	 */
	private String caseType;

	private String parentType;

	private String subType;

	/**
	 * @see com.fxs.platform.utils.CaseStatus
	 */
	private String status = CaseStatus.NEW.getStatus();
	
	//免费法律咨询额外信息
	private String comments;
	
	//免费法律咨询额外信息
	private String lawyerComments;
	
	private Date createAt = new Date();
	
	private String userId;

	private String lawyerId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLawyerId() {
		return lawyerId;
	}

	public void setLawyerId(String lawyerId) {
		this.lawyerId = lawyerId;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getLawyerComments() {
		return lawyerComments;
	}

	public void setLawyerComments(String lawyerComments) {
		this.lawyerComments = lawyerComments;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}
