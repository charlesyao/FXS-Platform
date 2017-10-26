package com.fxs.platform.security.core.properties;

/**
 * @author Charles
 *
 */
public class SmsCodeProperties {

	private int length = 6;

	private int expireIn = 60;
	/**
	 * 要拦截的url，多个url用逗号隔开，ant pattern
	 */
	private String url;

	public int getLength() {
		return length;
	}

	public void setLength(int lenght) {
		this.length = lenght;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
