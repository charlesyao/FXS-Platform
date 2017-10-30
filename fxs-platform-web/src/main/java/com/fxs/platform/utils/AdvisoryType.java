package com.fxs.platform.utils;

/**
 * 
 * @author Charles
 *
 */
public enum AdvisoryType {
	FREE("0"), PHONE("1");

	private String type;

	private AdvisoryType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
