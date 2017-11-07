package com.fxs.platform.security.core.authentication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * Extension configuration. Business system can override the default Bean
 * definition
 * 
 */
@Configuration
public class AuthenticationBeanConfig {

	@Bean
	@ConditionalOnMissingBean(PasswordEncoder.class)
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@ConditionalOnMissingBean(UserDetailsService.class)
	public UserDetailsService userDetailsService() {
		return new DefaultUserDetailsService();
	}
	
}
