package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.UserProfile;

public interface RoleService {
	public List<UserProfile> findAll();
	
	UserProfile findById(int id);
}
