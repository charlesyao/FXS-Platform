package com.fxs.platform.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.ObjectUtils;

/**
 * 案件所有主要信息封装类，如想获得更多信息，可进一步扩展此类
 *
 */
public class CasesDto {

	private String id;

	private String parentLocation;

	private String subLocation;
	
	private String caseType;

	private String parentType;

	private String subType;

	private String status;

	// 免费法律咨询额外信息
	private String comments;

	private Date createAt;
	
	private Date expiredDate;

	private String userId;

	private String isRead;

	private int feedbackCount;

	// List of QA mapping
	private List<CaseQuestionAnswerRelDto> qaMapping;

	private List<CaseFeedbackInfoDto> caseFeedbackInfo;

	private List<String> detailedInquiries;
	
	private boolean disableFeedback;
	
	private String acceptedLawyerFeedback;

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

	public List<CaseQuestionAnswerRelDto> getQaMapping() {
		return qaMapping;
	}

	public void setQaMapping(List<CaseQuestionAnswerRelDto> qaMapping) {
		this.qaMapping = qaMapping;
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

	public String getFalltypusType() {
		return this.getParentType() + (ObjectUtils.isEmpty(this.getSubType()) ? "" : "-" + this.getSubType());
	}

	public int getFeedbackCount() {
		return feedbackCount;
	}

	public void setFeedbackCount(int feedbackCount) {
		this.feedbackCount = feedbackCount;
	}

	public List<CaseFeedbackInfoDto> getCaseFeedbackInfo() {
		return caseFeedbackInfo;
	}

	public void setCaseFeedbackInfo(List<CaseFeedbackInfoDto> caseFeedbackInfo) {
		this.caseFeedbackInfo = caseFeedbackInfo;
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
	
	public String getLocation() {
		return this.getParentLocation() + (ObjectUtils.isEmpty(this.getSubLocation()) ? "" : "-" + this.getSubLocation());
	}

	public boolean isDisableFeedback() {
		return disableFeedback;
	}

	public void setDisableFeedback(boolean disableFeedback) {
		this.disableFeedback = disableFeedback;
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
