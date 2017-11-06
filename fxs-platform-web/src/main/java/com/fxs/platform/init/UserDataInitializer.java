package com.fxs.platform.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fxs.platform.domain.Role;
import com.fxs.platform.domain.User;
import com.fxs.platform.domain.UserRole;
import com.fxs.platform.repository.RoleRepository;
import com.fxs.platform.repository.UserRepository;
import com.fxs.platform.repository.UserRoleRepository;

@Component
public class UserDataInitializer extends AbstractDataInitializer {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public Integer getIndex() {
		return Integer.MIN_VALUE;
	}

	@Override
	protected void doInit() {
		List<String> roles = Arrays.asList("USER", "ADMIN", "LAWER");
		List<String> users = Arrays.asList("user", "admin", "lawer");
		
		for (int i = 0; i < 3; i ++) {
			Role role = initRole(roles.get(i));
			initUser(role, users.get(i));
		}
	}

	
	private Role initRole(String type) {
		Role role = new Role();
		role.setType(type);

		return roleRepository.save(role);
	}

	private void initUser(Role role, String type) {
		User user = new User();
		user.setUsername(type);
		user.setPassword(passwordEncoder.encode("123456"));
		userRepository.save(user);
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleRepository.save(userRole);
	}
	
	@Override
	protected boolean isNeedInit() {
		return userRepository.count() == 0;
	}
}
