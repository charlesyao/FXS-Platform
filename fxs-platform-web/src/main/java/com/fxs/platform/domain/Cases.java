package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cases {
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
	private String id;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
