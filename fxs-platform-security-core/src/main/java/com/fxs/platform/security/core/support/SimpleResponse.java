package com.fxs.platform.security.core.support;

import java.io.Serializable;

/**
 * 
 * @author Charles
 * @param <T>
 *
 */
public class SimpleResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String message;
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public SimpleResponse(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

}
