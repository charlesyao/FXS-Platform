package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.City;
import com.fxs.platform.dto.CityDto;
import com.fxs.platform.repository.CityRepository;
import com.fxs.platform.repository.condition.CityCondition;
import com.fxs.platform.repository.specification.CitySpecification;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.CityService;

/**
 * 
 * @author Charles
 *
 */
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;

	@Override
	@CachePut(value = "__citydata__")
	public City save(City city) {
		return cityRepository.save(city);
	}

	@Override
	public long count() {
		return cityRepository.count();
	}

	@Override
	@Cacheable(value = "__provinces__", key = "#parentCityId")
	public List<City> findProvinceByParentCityId(String parentCityId) {
		return cityRepository.findProvinceByParentCityId(parentCityId);
	}

	@Override
	@Cacheable("__level_1_cities__")
	public List<City> findFirstLevelCities() {
		return cityRepository.findFirstLevelCities(new Sort("name"));
	}

	@Override
	@Cacheable("__enabled_level_1_cities__")
	public List<CityDto> query(CityCondition condition) {
		List<City> enabledCity = cityRepository.findAll(new CitySpecification(condition));
		return QueryResultConverter.convert(enabledCity, CityDto.class);
	}
}
