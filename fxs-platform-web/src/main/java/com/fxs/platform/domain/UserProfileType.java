package com.fxs.platform.domain;

import java.io.Serializable;

public enum UserProfileType implements Serializable {
	USER("USER"), 
	LAWYER("LAWYER"), 
	ADMIN("ADMIN");

	String userProfileType;

	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}

}
