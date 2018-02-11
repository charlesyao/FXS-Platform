package com.fxs.platform.dto;

import java.time.LocalDateTime;

/**
 *案件反馈信息的封装类 
 *
 */
public class CaseFeedbackInfoDto {

	private String id;
	
	private String caseId;
	
	private String lawyerId;
	
	private String lawyerName;
	
	//律师费用
	private String counselFee;
	
	//免费法律咨询额外信息
	private String lawyerComments;
	
	private String status;
	
	private LocalDateTime createAt;
	
	private LocalDateTime updateAt;
	
	private String approvedBy;
	
	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
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

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getLawyerId() {
		return lawyerId;
	}

	public void setLawyerId(String lawyerId) {
		this.lawyerId = lawyerId;
	}

	public String getCounselFee() {
		return counselFee;
	}

	public void setCounselFee(String counselFee) {
		this.counselFee = counselFee;
	}

	public String getLawyerComments() {
		return lawyerComments;
	}

	public void setLawyerComments(String lawyerComments) {
		this.lawyerComments = lawyerComments;
	}

	public String getLawyerName() {
		return lawyerName;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}
}
