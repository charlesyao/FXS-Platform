package com.fxs.platform.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fxs.platform.domain.City;
import com.fxs.platform.domain.User;
import com.fxs.platform.dto.UserDto;
import com.fxs.platform.repository.condition.UserCondition;
import com.fxs.platform.service.CityService;
import com.fxs.platform.service.UserService;
import com.fxs.platform.view.View;

/**
 * @author Charles
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	CityService cityService;

	@GetMapping("/me")
	public UserDto me(@AuthenticationPrincipal UserDetails user) {
		UserDto info = new UserDto();
		info.setUsername(user.getUsername());
		return info;
	}

	@PostMapping("/collectformdata")
	@JsonView(View.DetailView.class)
	public User create(@RequestBody User userInfo) {
		return userService.create(userInfo);
	}

	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		userService.delete(id);
	}

	@PutMapping("/{id:\\d+}")
	public User update(@PathVariable String id, @Valid @RequestBody User user, BindingResult errors) {

		return userService.update(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param condition
	 * @param pageable
	 * @return
	 */
	@GetMapping
	public Page<UserDto> query(UserCondition condition, Pageable pageable) {
		condition.setUsername("admi");
		condition.setMobile("13");
		return userService.query(condition, pageable);
	}
}
