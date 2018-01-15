package com.fxs.platform.dto;


import java.io.Serializable;

/**
 * 电话咨询
 * 
 */
public class ReservationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String status;

	private String researvationDatetime;

	private String expiredDate;

	private String createAt;

	private String updateAt;

	private String contactPhone;

	private String location;

	private String falltypusType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResearvationDatetime() {
		return researvationDatetime;
	}

	public void setResearvationDatetime(String researvationDatetime) {
		this.researvationDatetime = researvationDatetime;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

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

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFalltypusType() {
		return falltypusType;
	}

	public void setFalltypusType(String falltypusType) {
		this.falltypusType = falltypusType;
	}
}
