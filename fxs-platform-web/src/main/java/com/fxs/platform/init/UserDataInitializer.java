package com.fxs.platform.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fxs.platform.domain.User;
import com.fxs.platform.repository.UserRepository;

/**
 * Default data initializer
 * 
 * @author Charles
 *
 */
@Component
public class UserDataInitializer extends AbstractDataInitializer {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Integer getIndex() {
		return Integer.MIN_VALUE;
	}

	@Override
	protected void doInit() {
		initUser();
	}

	private void initUser() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("123456"));
		userRepository.save(user);
	}

	@Override
	protected boolean isNeedInit() {
		return userRepository.count() == 0;
	}
}
