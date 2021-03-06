package com.fxs.platform.utils;

public enum CaseStatus {
	/**
	 * 新案件
	 */
	NEW("0"), 
	
	/**
	 * 律师已反馈
	 */
	FEEDBACK("1"), 
	
	/**
	 * 管理员已批准
	 */
	APPROVED("2"), 
	
	/**
	 * 管理员已拒绝
	 */
	DISAVOWED("3"), 
	
	/**
	 * 已竞标
	 */
	BID("4"),
	
	/**
	 * 案件结束
	 */
	END("5"),
	
	/**
	 * 已读
	 */
	READ("Y"),
	
	/**
	 * 未读
	 */
	UNREAD("N");

	private String status;

	private CaseStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
