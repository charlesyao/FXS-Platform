package com.fxs.platform.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Charles
 *
 */
@ConfigurationProperties(prefix = "fxs.security")
public class SecurityProperties {

	/**
	 * configurations for browser env
	 */
	private BrowserProperties browser = new BrowserProperties();

	/**
	 * validate code configuration
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();

	/**
	 * message configuration
	 * 
	 * @return
	 */
	private MessageProperties message = new MessageProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public MessageProperties getMessage() {
		return message;
	}

	public void setMessage(MessageProperties message) {
		this.message = message;
	}

}
