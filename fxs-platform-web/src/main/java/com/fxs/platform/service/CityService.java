package com.fxs.platform.service;

import com.fxs.platform.domain.City;

/**
 * 
 * @author Charles
 *
 */
public interface CityService {
	City save(City city);
	
	long count();
}
