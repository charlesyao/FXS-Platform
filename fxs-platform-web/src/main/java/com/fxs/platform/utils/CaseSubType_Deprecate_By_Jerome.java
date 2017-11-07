package com.fxs.platform.utils;

public enum CaseSubType_Deprecate_By_Jerome {
	PHONE("0"), SEL_SERVICE("1");

	private String type;

	private CaseSubType_Deprecate_By_Jerome(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
