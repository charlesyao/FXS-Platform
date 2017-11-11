package com.fxs.platform.service.impl;

import java.util.ArrayList;
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
		List<Role> rList = roleRepository.findAll();
		List<Role> nrList = new ArrayList<Role>();

		for (Role role : rList) {
			Role r = new Role();
			switch (role.getType()) {
				case "USER":
					r.setId(role.getId());
					r.setType("当事人");
					break;
				case "LAWYER":
					r.setId(role.getId());
					r.setType("律师");
					break;
				case "ADMIN":
					r.setId(role.getId());
					r.setType("管理员");
					break;
				default:
					break;
			}

			nrList.add(r);
		}

		return nrList;
	}

}
