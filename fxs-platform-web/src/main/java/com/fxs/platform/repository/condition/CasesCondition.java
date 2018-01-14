package com.fxs.platform.repository.condition;


/**
 *案件查找条件封装 
 *
 */
public class CasesCondition {

	private String[] parentType;

	private String id;

	private String status;
	
	private String caseType;
	
	private String userId;
	
	private String isRead;
	
	private String searchFrom;
	
	private String requestFrom;
	
	private int feedbackCount;
	
	private String createAt;

	public int getFeedbackCount() {
		return feedbackCount;
	}

	public void setFeedbackCount(int feedbackCount) {
		this.feedbackCount = feedbackCount;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getParentType() {
		return parentType;
	}

	public void setParentType(String[] parentType) {
		this.parentType = parentType;
	}

	public String getSearchFrom() {
		return searchFrom;
	}

	public void setSearchFrom(String searchFrom) {
		this.searchFrom = searchFrom;
	}

	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
}
