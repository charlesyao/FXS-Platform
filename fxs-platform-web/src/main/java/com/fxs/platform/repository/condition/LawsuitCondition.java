package com.fxs.platform.repository.condition;

public class LawsuitCondition {

	private String id;

	private String type;

	private String status;

	private String[] falltypus;

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

	public String[] getFalltypus() {
		return falltypus;
	}

	public void setFalltypus(String[] falltypus) {
		this.falltypus = falltypus;
	}
}
