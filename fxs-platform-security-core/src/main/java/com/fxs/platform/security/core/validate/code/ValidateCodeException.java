package com.fxs.platform.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Charles
 *
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7285211528095468156L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
