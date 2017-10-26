package com.fxs.platform.security.core.authorize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;

/**
 * Default Authorize Config Manager
 * 
 * @author Charles
 *
 */
@Component
public class FxsAuthorizeConfigManager implements AuthorizeConfigManager {

	@Autowired
	private List<AuthorizeConfigProvider> authorizeConfigProviders;
	
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		boolean existAnyRequestConfig = false;
		String existAnyRequestConfigName = null;

		for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
			boolean currentIsAnyRequestConfig = authorizeConfigProvider.config(config);
			if (existAnyRequestConfig && currentIsAnyRequestConfig) {
				throw new RuntimeException(
						localeMessageSourceService.getMessage("fxs.platform.core.request.authorize.error-config", 
								new Object[] {existAnyRequestConfigName, authorizeConfigProvider.getClass().getSimpleName()}));
			} else if (currentIsAnyRequestConfig) {
				existAnyRequestConfig = true;
				existAnyRequestConfigName = authorizeConfigProvider.getClass().getSimpleName();
			}
		}

		if (!existAnyRequestConfig) {
			config.anyRequest().authenticated();
		}
	}
}
