package com.fxs.platform.utils;

public enum CaseType {
	/**
	 * 打官司
	 */
	CONSULTING("0"), 
	
	/**
	 * 法律咨询
	 */
	LAWSUIT("1"),
	
	RESERVATION("2");

	private String type;

	private CaseType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
