package com.fxs.platform.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fxs.platform.security.core.validate.code.sms.SmsCodeSender;

public class CustomizedSmsCodeSender implements SmsCodeSender {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void send(String mobile, String code) {
		logger.info("Real SMS code sender");
	}
}
