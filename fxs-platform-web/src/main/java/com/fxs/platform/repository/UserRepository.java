package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.User;

@Repository
public interface UserRepository extends FxsRepository<User> {

	User findByUsername(String username);
	
	@Query("SELECT o FROM User o WHERE o.username=?1")
	List<User> checkUserExist(String name);

	User findByMobile(String mobile);

}
