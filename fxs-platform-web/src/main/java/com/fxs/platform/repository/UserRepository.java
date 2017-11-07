package com.fxs.platform.repository;

import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.User;

@Repository
public interface UserRepository extends FxsRepository<User> {

	User findByUsername(String username);

	User findByMobile(String mobile);

}
