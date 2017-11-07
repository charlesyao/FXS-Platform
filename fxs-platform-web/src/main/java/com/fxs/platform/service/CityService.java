package com.fxs.platform.service;

import java.util.HashMap;
import java.util.List;

import com.fxs.platform.domain.City;
import com.fxs.platform.dto.CityDto;
import com.fxs.platform.repository.condition.CityCondition;

public interface CityService {
	City save(City city);

	long count();

	HashMap<String, List<CityDto>> loadEnabledCities();

	List<CityDto> findFirstLevelCities();

	List<CityDto> findProvinceByParentCityId(String parentCityId);

	List<CityDto> query(CityCondition condition);
}
