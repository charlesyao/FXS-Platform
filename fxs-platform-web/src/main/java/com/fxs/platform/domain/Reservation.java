package com.fxs.platform.domain;

import javax.persistence.*;

import com.fxs.platform.utils.CaseStatus;
import com.fxs.platform.utils.DateUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
	private LocalDateTime researvationDatetime;

	private LocalDateTime expiredDate;

	private LocalDateTime createAt = LocalDateTime.now();

	private LocalDateTime updateAt =  LocalDateTime.now();
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getResearvationDatetime() {
		return researvationDatetime;
	}

	public void setResearvationDatetime(LocalDateTime researvationDatetime) {
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

	public LocalDateTime getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate() {
		//默認一周過期
		this.expiredDate = DateUtil.getFetureDate(this.getResearvationDatetime());
	}
}
