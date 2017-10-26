package com.fxs.platform.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.fxs.platform.security.browser.logout.FxsLogoutSuccessHandler;
import com.fxs.platform.security.browser.session.FxsExpiredSessionStrategy;
import com.fxs.platform.security.browser.session.FxsInvalidSessionStrategy;
import com.fxs.platform.security.core.properties.SecurityProperties;

/**
 * Extension configuration. Business system can override the default Bean
 * definition
 * 
 * @author Charles
 *
 */
@Configuration
public class BrowserSecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy() {
		return new FxsInvalidSessionStrategy(securityProperties);
	}

	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
		return new FxsExpiredSessionStrategy(securityProperties);
	}

	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new FxsLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
	}
}
