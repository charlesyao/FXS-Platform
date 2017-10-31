package com.fxs.platform.utils;

/**
 * 
 * @author Charles
 *
 */
public enum ConsultationType {
	/**
	 * 免费法律咨询
	 */
	FREE("0"), 
	
	/**
	 * 电话咨询
	 */
	PHONE("1");

	private String type;

	private ConsultationType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
