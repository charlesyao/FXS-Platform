/**
 * 
 */
package com.fxs.platform.utils;

/**
 * @author Charles
 *
 */
public enum CaseStatus {
	NEW("0"), FEEDBACK("1"), APPROVED("2"), DISAVOWED("3"), BID("4"), END("5");

	private String status;

	private CaseStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
