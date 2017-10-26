package com.fxs.platform.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;

/**
 * Default SMS code sender
 * 
 * @author Charles
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Override
	public void send(String mobile, String code) {
		logger.warn(localeMessageSourceService.getMessage("fxs.platform.core.validation.code.sms-sender-not-exist"));
		logger.info(localeMessageSourceService.getMessage("fxs.platform.core.validation.code.send-sms", 
				new Object[] {code, mobile}));
	}

}
