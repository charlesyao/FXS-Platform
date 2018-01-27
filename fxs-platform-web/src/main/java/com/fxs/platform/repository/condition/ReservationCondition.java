package com.fxs.platform.repository.condition;

import java.time.LocalDateTime;

/**
 *案件查找条件封装 
 *
 */
public class ReservationCondition {

	private String[] status;
	
	private String isRead;
	
	private String searchFrom;
	
	private String requestFrom;

	private String createAt;
	
	private LocalDateTime researvationDatetime;

	public LocalDateTime getResearvationDatetime() {
		return researvationDatetime;
	}

	public void setResearvationDatetime(LocalDateTime researvationDatetime) {
		this.researvationDatetime = researvationDatetime;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}

	public String getSearchFrom() {
		return searchFrom;
	}

	public void setSearchFrom(String searchFrom) {
		this.searchFrom = searchFrom;
	}

	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
}
