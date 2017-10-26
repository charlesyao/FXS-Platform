package com.fxs.platform.security.browser;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.fxs.platform.security.core.authentication.FormAuthenticationConfig;
import com.fxs.platform.security.core.authorize.AuthorizeConfigManager;
import com.fxs.platform.security.core.properties.SecurityConstants;
import com.fxs.platform.security.core.properties.SecurityProperties;
import com.fxs.platform.security.core.validate.code.ValidateCodeSecurityConfig;

/**
 * 
 * @author Charles
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;

	@Autowired
	private FormAuthenticationConfig formAuthenticationConfig;

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		if(StringUtils.isNotBlank(securityProperties.getBrowser().getIgnoringUrl())) {
			web.ignoring().antMatchers(
					StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getBrowser().getIgnoringUrl(), ","));
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		formAuthenticationConfig.configure(http);

		http.apply(validateCodeSecurityConfig)
				.and()
				.rememberMe()
					.tokenRepository(persistentTokenRepository())
					.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
					.userDetailsService(userDetailsService)
				.and()
				.sessionManagement()
					.invalidSessionStrategy(invalidSessionStrategy)
					.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
					.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
					.expiredSessionStrategy(sessionInformationExpiredStrategy)
				.and()
				.and()
				.logout()
					.logoutUrl(SecurityConstants.DEFAULT_SIGN_OUT_PROCESSING_URL)
					.logoutSuccessHandler(logoutSuccessHandler)
					.deleteCookies("JSESSIONID")
				.and()
				.csrf().disable();

		authorizeConfigManager.config(http.authorizeRequests());

	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
}
