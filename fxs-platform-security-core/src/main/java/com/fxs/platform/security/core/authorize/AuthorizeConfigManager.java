package com.fxs.platform.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Authorize Config Manager
 * 
 * Collect all AuthorizeConfigProvider and load the related configuration
 * 
 *
 */
public interface AuthorizeConfigManager {

	/**
	 * @param config
	 */
	void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
