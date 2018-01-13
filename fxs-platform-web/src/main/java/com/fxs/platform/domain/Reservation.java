package com.fxs.platform.domain;

import javax.persistence.*;

import com.fxs.platform.utils.CaseStatus;
import com.fxs.platform.utils.DateUtil;

import java.io.Serializable;

/**
 * 电话咨询
 * 
 */
@Entity
public class Reservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	/**
	 * 预约状态
	 */
	private String status = CaseStatus.NEW.getStatus();

	/**
	 * 当事人和律师预约电话联系的日期+时间
	 */
	private String researvationDatetime;

	private String expiredDate;
	
	private String createAt = DateUtil.getCurrentDate();
	
	private String updateAt = DateUtil.getCurrentDate();
	
	/**
	 * 当事人的联系方式
	 */
	private String contactPhone;

	/**
	 * 预约用户
	 */
	private String userId="";

	/**
	 * 响应律师
	 */
	private String lawyerId;
	
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

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLawyerId() {
		return lawyerId;
	}

	public void setLawyerId(String lawyerId) {
		this.lawyerId = lawyerId;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate() {
		//默認一周過期
		this.expiredDate = DateUtil.getFetureDate(this.getResearvationDatetime(), 7);
	}
}
