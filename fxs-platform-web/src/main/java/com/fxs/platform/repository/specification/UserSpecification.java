package com.fxs.platform.repository.specification;

import com.fxs.platform.domain.User;
import com.fxs.platform.repository.condition.UserCondition;
import com.fxs.platform.repository.support.FxsSpecification;
import com.fxs.platform.repository.support.QueryWraper;

public class UserSpecification extends FxsSpecification<User, UserCondition> {

	public UserSpecification(UserCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<User> queryWraper) {
		addLikeCondition(queryWraper, "username");
		addLikeCondition(queryWraper, "mobile");
	}
}
