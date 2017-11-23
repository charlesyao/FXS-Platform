package com.fxs.platform.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fxs.platform.domain.User;
import com.fxs.platform.dto.UserDto;
import com.fxs.platform.repository.condition.UserCondition;

public interface UserService {

	User create(User userInfo);
	
	void delete(String id);
	
	User update(String id);
	
	List<User> findOne(String username);

	Page<UserDto> query(UserCondition condition, Pageable pageable);
}
