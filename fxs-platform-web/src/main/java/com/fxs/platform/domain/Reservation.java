package com.fxs.platform.domain;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
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
	@GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
	private String id;

	/**
	 * 预约状态
	 */
	private String status;

	/**
	 * 当事人和律师预约电话联系的日期+时间
	 */
	private Date researvationDatetime;

	/**
	 * 当事人的联系方式
	 */
	private String contactPhone;

	/**
	 * 预约用户
	 */
	private String userId;

	/**
	 * 响应律师
	 */
	private String lawyerId;

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

	public Date getResearvationDatetime() {
		return researvationDatetime;
	}

	public void setResearvationDatetime(Date researvationDatetime) {
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
}
