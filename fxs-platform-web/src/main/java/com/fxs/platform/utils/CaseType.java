package com.fxs.platform.utils;

/**
 * 
 * @author Charles
 *
 */
public enum CaseType {
	ADVISORY("0"), LAWSUIT("1");

	private String type;

	private CaseType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
