package com.fxs.platform.utils;

public enum QuestionType {
	SINGLE_SELECT("0"), MULTI_SELECT("1"), FREE_TEXT("2"), RADIO("3"), CHECKBOX("4"), DROPDOWN("5");

	private String type;

	private QuestionType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
