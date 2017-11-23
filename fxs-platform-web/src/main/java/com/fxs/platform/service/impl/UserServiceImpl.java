package com.fxs.platform.service.impl;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxs.platform.domain.User;
import com.fxs.platform.dto.UserDto;
import com.fxs.platform.repository.UserRepository;
import com.fxs.platform.repository.condition.UserCondition;
import com.fxs.platform.repository.specification.UserSpecification;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreatedTime(LocalDate.now().toDate());
		return userRepository.save(user);
	}

	@Override
	public Page<UserDto> query(UserCondition condition, Pageable pageable) {
		Page<User> users = userRepository.findAll(new UserSpecification(condition), pageable);
		return QueryResultConverter.convert(users, UserDto.class, pageable);
	}

	@Override
	public void delete(String id) {
		userRepository.delete(Integer.parseInt(id));
	}

	@Override
	public User update(String id) {
		return null;
	}

	@Override
	public List<User> findOne(String username) {
		// TODO Auto-generated method stub
		return userRepository.checkUserExist(username);
	}
}
