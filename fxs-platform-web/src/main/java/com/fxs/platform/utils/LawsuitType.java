package com.fxs.platform.utils;

/**
 * 
 * @author Charles
 *
 */
public enum LawsuitType {
	LAWER("0"), PHONE("1");

	private String type;

	private LawsuitType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
