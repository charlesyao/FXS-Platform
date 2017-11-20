package com.fxs.platform.utils;

public enum CaseType {
	/**
	 * 打官司
	 */
	CONSULTATION("0"), 
	
	/**
	 * 法律咨询
	 */
	LAWSUIT("1");

	private String type;

	private CaseType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
