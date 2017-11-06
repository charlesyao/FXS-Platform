package com.fxs.platform.repository.specification;

import com.fxs.platform.domain.City;
import com.fxs.platform.repository.condition.CityCondition;
import com.fxs.platform.repository.support.FxsSpecification;
import com.fxs.platform.repository.support.QueryWraper;

public class CitySpecification extends FxsSpecification<City, CityCondition> {

	public CitySpecification(CityCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<City> queryWraper) {
		addInCondition(queryWraper, "cityId");
		addEqualsCondition(queryWraper, "parentCityId");
		addEqualsCondition(queryWraper, "level");
	}
}
