package com.fxs.platform.security.core.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;

public class DefaultUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.warn(
				localeMessageSourceService.getMessage("fxs.platform.core.authentication.user-detail-service"));
		throw new UsernameNotFoundException(username);
	}

}
