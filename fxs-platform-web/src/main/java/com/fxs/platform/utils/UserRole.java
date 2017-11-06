package com.fxs.platform.utils;

public enum UserRole {
	LITIGANT("0"), LAWER("1"), ADMIN("2");

	private String type;

	private UserRole(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
