package com.fxs.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.UserProfile;
import com.fxs.platform.repository.RoleRepository;
import com.fxs.platform.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	@Cacheable(value = "roles")
	public List<UserProfile> findAll() {
		List<UserProfile> rList = roleRepository.findAll();
		List<UserProfile> nrList = new ArrayList<UserProfile>();

		for (UserProfile role : rList) {
			UserProfile r = new UserProfile();
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

	@Override
	public UserProfile findById(int id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id);
	}

}
