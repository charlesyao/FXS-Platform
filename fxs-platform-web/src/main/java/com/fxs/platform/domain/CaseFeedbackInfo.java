package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class CaseFeedbackInfo {

	@Id
	@SequenceGenerator(name = "seq_contacts", sequenceName = "seq_contacts")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_contacts")
	private int id;
	
	private String caseId;
	
	private String lawyerId;
	
	private String lawyerName;
	
	//律师费用
	private String counselFee;
	
	//免费法律咨询额外信息
	private String lawyerComments;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
