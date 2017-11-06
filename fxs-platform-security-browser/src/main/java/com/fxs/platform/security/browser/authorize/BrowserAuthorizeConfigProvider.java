package com.fxs.platform.security.browser.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.fxs.platform.security.core.authorize.AuthorizeConfigProvider;

/**
 * Default authorize configuration for static resources, js, css, image, etc
 * 
 */
@Component
@Order(Integer.MIN_VALUE)
public class BrowserAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(HttpMethod.GET, 
			"/**/*.js",
			"/**/*.css",
			"/**/*.jpg",
			"/**/*.png",
			"/**/*.gif").permitAll();
		return false;
	}
}
