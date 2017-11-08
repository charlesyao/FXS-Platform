package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consultation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * 法律咨询类型： 免费咨询/电话咨询
	 * 
	 * @see com.fxs.platform.utils.LawsuitType
	 */
	private String type;

	/**
	 * 案件状态
	 * 
	 * @see com.fxs.platform.utils.CaseStatus
	 */
	private String status;

	/**
	 * 案件类型: 劳动纠纷, 刑事案件...
	 */
	private String falltypus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFalltypus() {
		return falltypus;
	}

	public void setFalltypus(String falltypus) {
		this.falltypus = falltypus;
	}
}
