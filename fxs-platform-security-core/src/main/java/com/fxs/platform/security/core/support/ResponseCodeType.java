package com.fxs.platform.security.core.support;

/**
 * 
 * @author Charles
 *
 */
public enum ResponseCodeType {
	MINUS_ONE(-1),
	ZERO(0);

	private int value;

	private ResponseCodeType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
