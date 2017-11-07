package com.fxs.platform.utils;

public enum LawsuitType {
	/**
	 * 找律师打官司
	 */
	LAWER("0"), 
	
	/**
	 * 自助打官司
	 */
	PHONE("1");

	private String type;

	private LawsuitType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
