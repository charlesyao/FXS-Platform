package com.fxs.platform.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.ObjectUtils;

public class CasesDto {
	
	private String id;

	private String caseType;

	private String parentType;

	private String subType;

	private String status;
	
	//免费法律咨询额外信息
	private String comments;
	
	private String counselFee;
	
	private String lawyerComments;
	
	private Date createAt;

	private String userId;

	private String lawyerId;
	
	private String isRead;
	
	private int feedbackCount;
	
	//List of QA mapping
	private List<CaseQuestionAnswerRelDto> qaMapping;
	
	private List<String> detailedInquiries;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public List<CaseQuestionAnswerRelDto> getQaMapping() {
		return qaMapping;
	}

	public void setQaMapping(List<CaseQuestionAnswerRelDto> qaMapping) {
		this.qaMapping = qaMapping;
	}

	public String getLawyerComments() {
		return lawyerComments;
	}

	public void setLawyerComments(String lawyerComments) {
		this.lawyerComments = lawyerComments;
	}

	public String getCreateAt() {
		return DateFormatUtils.format(createAt, "yyyy-MM-dd HH:MM:SS");
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<String> getDetailedInquiries() {
		return detailedInquiries;
	}

	public void setDetailedInquiries(List<String> detailedInquiries) {
		this.detailedInquiries = detailedInquiries;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getCounselFee() {
		return counselFee;
	}

	public void setCounselFee(String counselFee) {
		this.counselFee = counselFee;
	}
	
	public String getFalltypusType () {
		return this.getParentType() + (ObjectUtils.isEmpty(this.getSubType()) ? "" : "-" + this.getSubType());
	}

	public int getFeedbackCount() {
		return feedbackCount;
	}

	public void setFeedbackCount(int feedbackCount) {
		this.feedbackCount = feedbackCount;
	}
}
