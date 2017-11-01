package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.City;
import com.fxs.platform.dto.CityDto;
import com.fxs.platform.repository.condition.CityCondition;

/**
 * 
 * @author Charles
 *
 */
public interface CityService {
	City save(City city);

	long count();

	List<City> findFirstLevelCities();

	List<City> findProvinceByParentCityId(String parentCityId);

	List<CityDto> query(CityCondition condition);
}
