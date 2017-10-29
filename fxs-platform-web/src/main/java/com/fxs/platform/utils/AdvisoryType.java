package com.fxs.platform.utils;

/**
 * 
 * @author Charles
 *
 */
public enum AdvisoryType {
	FREE(0), PHONE(1);

	private int type;

	private AdvisoryType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
