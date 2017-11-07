package com.fxs.platform.utils;

public enum CaseType_Deprecate_By_Jerome {
	/**
	 * 打官司
	 */
	CONSULTATION("0"), 
	
	/**
	 * 法律咨询
	 */
	LAWSUIT("1");

	private String type;

	private CaseType_Deprecate_By_Jerome(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
