/**
 * 
 */
package com.fxs.platform.utils;

/**
 * @author Charles
 *
 */
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
	END("5");

	private String status;

	private CaseStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
