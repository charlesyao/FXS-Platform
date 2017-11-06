package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.Role;
import com.fxs.platform.repository.RoleRepository;
import com.fxs.platform.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	@Cacheable(value = "roles")
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

}
