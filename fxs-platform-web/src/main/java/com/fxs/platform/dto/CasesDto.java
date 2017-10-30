package com.fxs.platform.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Charles
 *
 */
public class CasesDto {
	private Long id;

	/**
	 * 主类型：法律咨询或者打官司
	 */
	private String type;

	/**
	 * 子类型： 免费咨询/找律师打官司/电话咨询
	 */
	private String subType;

	/**
	 * 案件当前状态
	 * 
	 * @see com.fxs.platform.utils.CaseStatus
	 */
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}
