package com.fxs.platform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fxs.platform.domain.Role;

public interface RoleService {
	public List<Role> findAll();
}
