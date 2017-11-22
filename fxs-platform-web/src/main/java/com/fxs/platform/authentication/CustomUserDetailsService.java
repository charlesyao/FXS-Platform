package com.fxs.platform.authentication;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fxs.platform.domain.User;
import com.fxs.platform.domain.UserProfile;
import com.fxs.platform.repository.UserRepository;
import com.fxs.platform.utils.SystemConstants;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    HttpSession session;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		logger.info("User : " + user);
		if (user == null) {
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		
		session.setAttribute(SystemConstants.USER_INFO, user);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getState().equals("Active"), true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserProfile userProfile : user.getUserProfiles()) {
			logger.info("UserProfile : " + userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
		}
		logger.info("authorities :" + authorities);
		return authorities;
	}

}
