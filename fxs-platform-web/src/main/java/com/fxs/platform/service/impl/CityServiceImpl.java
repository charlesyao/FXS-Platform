package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.City;
import com.fxs.platform.repository.CityRepository;
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
	public City save(City city) {
		return cityRepository.save(city);
	}

	@Override
	public long count() {
		return cityRepository.count();
	}

	@Override
	public List<City> findProvinceByParentCityId(String parentCityId) {
		return cityRepository.findProvinceByParentCityId(parentCityId);
	}
}
