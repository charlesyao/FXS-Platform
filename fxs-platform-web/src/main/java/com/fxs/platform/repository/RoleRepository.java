package com.fxs.platform.repository;

import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.UserProfile;

@Repository
public interface RoleRepository extends FxsRepository<UserProfile> {
	UserProfile findById(Integer id); 
}
