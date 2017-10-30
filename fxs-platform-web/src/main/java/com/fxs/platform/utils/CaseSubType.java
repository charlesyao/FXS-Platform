package com.fxs.platform.utils;

/**
 * 
 * @author Charles
 *
 */
public enum CaseSubType {
	PHONE("0"), SEL_SERVICE("1");

	private String type;

	private CaseSubType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
