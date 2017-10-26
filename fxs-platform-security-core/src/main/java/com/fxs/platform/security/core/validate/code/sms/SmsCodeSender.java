package com.fxs.platform.security.core.validate.code.sms;

/**
 * @author Charles
 *
 */
public interface SmsCodeSender {

	void send(String mobile, String code);

}
