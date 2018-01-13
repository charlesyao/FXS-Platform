package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import com.fxs.platform.utils.DateUtil;

/**
 * 案件类型领域模型
 * 
 *
 */
@Entity
public class Falltypus {
	@Id
	private String id;

	@NotBlank(message = "案件类型不能为空")
	private String name;

	private String parentTypeId = "";

	private String createAt = DateUtil.getCurrentDate();

	private String updateAt = DateUtil.getCurrentDate();

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentTypeId() {
		return parentTypeId;
	}

	public void setParentTypeId(String parentTypeId) {
		this.parentTypeId = parentTypeId;
	}

}
