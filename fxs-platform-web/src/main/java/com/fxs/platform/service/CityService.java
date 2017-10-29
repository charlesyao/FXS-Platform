package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.City;

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
}
