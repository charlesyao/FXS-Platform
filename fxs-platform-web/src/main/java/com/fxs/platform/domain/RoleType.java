package com.fxs.platform.domain;

public enum RoleType {
	USER("USER"), LAWER("LAWER"), ADMIN("ADMIN");

	String userProfileType;

	private RoleType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}

}
