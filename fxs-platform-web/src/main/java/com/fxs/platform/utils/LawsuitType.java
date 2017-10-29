package com.fxs.platform.utils;

/**
 * 
 * @author Charles
 *
 */
public enum LawsuitType {
	LAWER(0), PHONE(1);

	private int type;

	private LawsuitType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
