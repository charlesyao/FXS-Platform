package com.fxs.platform.domain;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


/**
 * 案件领域模型 免费法律咨询、打官司
 *
 */
@Entity
public class Cases {
	@Id
	private String id;

	private String parentLocation;
	
	private String subLocation;
	/**
	 * @see com.fxs.platform.utils.CaseType
	 */
	private String caseType;

	private String parentType;

	private String subType;

	/**
	 * @see com.fxs.platform.utils.CaseStatus
	 */
	private String status;
	
	//免费法律咨询额外信息
	private String comments;
	
	//标识当前免费的法律咨询共有多少个律师反馈, 最多十个
	private int feedbackCount;
	
	//是否已经查看
	private String isRead;
	
	@Transient
	private String detailedInquirys;
	
	private Date createAt = new Date();
	
	private Date expiredDate;
	
	private String acceptedLawyerFeedback;
	
	private String userId;

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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getDetailedInquirys() {
		return detailedInquirys;
	}

	public void setDetailedInquirys(String detailedInquirys) {
		this.detailedInquirys = detailedInquirys;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public int getFeedbackCount() {
		return feedbackCount;
	}

	public void setFeedbackCount(int feedbackCount) {
		this.feedbackCount = feedbackCount;
	}

	public String getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(String parentLocation) {
		this.parentLocation = parentLocation;
	}

	public String getSubLocation() {
		return subLocation;
	}

	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getAcceptedLawyerFeedback() {
		return acceptedLawyerFeedback;
	}

	public void setAcceptedLawyerFeedback(String acceptedLawyerFeedback) {
		this.acceptedLawyerFeedback = acceptedLawyerFeedback;
	}
}
