package com.fxs.platform.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.fxs.platform.security.core.authorize.AuthorizeConfigProvider;

/**
 * 系统URL授权配置
 *
 */
@Component
public class CustomizedAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		/*
		 * config .antMatchers(HttpMethod.GET, "/fxsIndex").permitAll()
		 * .antMatchers(HttpMethod.POST, "/user/collectformdata").permitAll()
		 * .anyRequest().authenticated(); return true; return false;
		 */

		config.antMatchers(
					"/",
					"/invalid-session",
					"/autosave/**", 
					"/lawyer/signIn",
					"/signOut",
					"/user/signIn",
					"/user/dashboard",
					"/user/register",
					"/user",
					"/disputeInfo/**",
					"/public/**")
			.permitAll().anyRequest().authenticated()
			.antMatchers("/bootstrap/**").permitAll();
		
		return true;
	}
}
