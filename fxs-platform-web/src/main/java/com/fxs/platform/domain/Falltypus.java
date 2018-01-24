package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import com.fxs.platform.utils.DateUtil;

import java.time.LocalDateTime;
import java.util.Date;

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

	private LocalDateTime createAt = LocalDateTime.now();

	private LocalDateTime updateAt =  LocalDateTime.now();

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
