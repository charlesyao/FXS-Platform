package com.fxs.platform.web.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxs.platform.domain.User;
import com.fxs.platform.dto.UserDto;
import com.fxs.platform.repository.condition.UserCondition;
import com.fxs.platform.service.CityService;
import com.fxs.platform.service.UserService;
import com.fxs.platform.utils.SystemConstants;

@Controller
@RequestMapping("/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	CityService cityService;

	@GetMapping("/me")
	@ResponseBody
	public UserDto me(@AuthenticationPrincipal UserDetails user) {
		UserDto info = new UserDto();
		info.setUsername(user.getUsername());
		return info;
	}

	@PostMapping
	public String create(@Valid User user, BindingResult result, ModelMap model) {
		
		if (ObjectUtils.isEmpty(userService.findOne(user.getUsername()))) {
			userService.create(user);
		} else {
			session.setAttribute(SystemConstants.DUPLICATE_USER, "用户名" + user.getUsername() + "已经存在");
			
			return "redirect:/user/register";
		}
		
		
		return "registSuccess";
	}

	@DeleteMapping("/{id:\\d+}")
	@ResponseBody
	public void delete(@PathVariable String id) {
		userService.delete(id);
	}

	@PutMapping("/{id:\\d+}")
	@ResponseBody
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
	@ResponseBody
	public Page<UserDto> query(UserCondition condition, Pageable pageable) {
		return userService.query(condition, pageable);
	}
}
