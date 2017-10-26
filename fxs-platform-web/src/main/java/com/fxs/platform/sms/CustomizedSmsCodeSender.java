package com.fxs.platform.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxs.platform.domain.User;
import com.fxs.platform.repository.UserRepository;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.validate.code.sms.SmsCodeSender;

/**
 * 
 * @author Charles
 *
 */
@Component
public class CustomizedSmsCodeSender implements SmsCodeSender {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Override
	public void send(String mobile, String code) {
		logger.info(localeMessageSourceService.getMessage("fxs.platform.core.validation.code.send-sms",
				new Object[] { code, mobile }));
		
		if(userRepository.findByMobile(mobile) == null) {
			User user = new User();
			user.setMobile(mobile);
			user.setUsername(mobile);
			userRepository.save(user);
		}
	}
}
