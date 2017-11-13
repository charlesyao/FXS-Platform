package com.fxs.platform.init;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fxs.platform.domain.User;
import com.fxs.platform.domain.UserProfile;
import com.fxs.platform.repository.RoleRepository;
import com.fxs.platform.repository.UserRepository;

@Component
public class UserDataInitializer extends AbstractDataInitializer {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;


	@Override
	public Integer getIndex() {
		return Integer.MIN_VALUE;
	}

	@Override
	protected void doInit() {
		List<String> roles = Arrays.asList("USER", "ADMIN", "LAWYER");
		List<String> users = Arrays.asList("user", "admin", "lawyer");
		
		for (int i = 0; i < 3; i ++) {
			UserProfile userProfile = initUserProfile(roles.get(i));
			initUser(userProfile, users.get(i));
		}
	}

	
	private UserProfile initUserProfile(String type) {
		UserProfile userProfile = new UserProfile();
		userProfile.setType(type);

		return roleRepository.save(userProfile);
	}

	private void initUser(UserProfile userProfiles, String type) {
		User user = new User();
		user.setUsername(type);
		user.setPassword(passwordEncoder.encode("123456"));
		
		Set<UserProfile> set = new HashSet<UserProfile>();
		set.add(userProfiles);
		
		user.setUserProfiles(set);
		userRepository.save(user);
	}
	
	@Override
	protected boolean isNeedInit() {
		return userRepository.count() == 0;
	}
}
