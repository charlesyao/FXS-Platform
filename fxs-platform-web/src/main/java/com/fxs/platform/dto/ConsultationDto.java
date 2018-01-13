package com.fxs.platform.dto;

/**
 *案件-免费法律咨询的信息封装 
 *
 */
public class ConsultationDto {

	private String id;

	private String type;

	private String status;

	private String falltypus;
	
	private String createAt;
	
	private String updateAt;

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
