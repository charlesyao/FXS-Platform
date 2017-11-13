package com.fxs.platform.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
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
import com.fxs.platform.support.EnabledCitySettings;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;

	@Autowired
	EnabledCitySettings enabledCitySettings;

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
	public List<CityDto> findProvinceByParentCityId(String parentCityId) {
		return QueryResultConverter.convert(cityRepository.findProvinceByParentCityId(parentCityId), CityDto.class);

	}

	@Override
	@Cacheable("__level_1_cities__")
	public List<CityDto> findFirstLevelCities() {
		String idStr = enabledCitySettings.getCityId();
		
		if (StringUtils.isNotBlank(idStr)) {
			return QueryResultConverter.convert(cityRepository.findFirstLevelCityByIds(StringUtils.splitByWholeSeparatorPreserveAllTokens(idStr, ","), new Sort("name")), CityDto.class);
		} else {
			return QueryResultConverter.convert(cityRepository.findFirstLevelCities(new Sort("name")), CityDto.class);
		}
	}
	
	@Override
	public List<CityDto> query(CityCondition condition) {
		List<City> enabledCity = cityRepository.findAll(new CitySpecification(condition));
		return QueryResultConverter.convert(enabledCity, CityDto.class);
	}

	@Override
	@Cacheable(value = "__enables_citites__")
	public HashMap<String, List<CityDto>> loadEnabledCities() {
		HashMap<String, List<CityDto>> cities = new HashMap<String, List<CityDto>>();

		if (StringUtils.isNotBlank(enabledCitySettings.getCityId())) {

			String[] enabledCityIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(enabledCitySettings.getCityId(), ",");

			for (String id : enabledCityIds) {
				List<CityDto> provinces = this.findProvinceByParentCityId(id);

				cities.put(id, provinces);
			}
		} else {
			List<CityDto> level1Cities = findFirstLevelCities();

			for (CityDto cityDto : level1Cities) {
				List<CityDto> provinces = this.findProvinceByParentCityId(cityDto.getCityId());

				cities.put(cityDto.getCityId(), provinces);
			}
		}

		return cities;
	}
	
}
