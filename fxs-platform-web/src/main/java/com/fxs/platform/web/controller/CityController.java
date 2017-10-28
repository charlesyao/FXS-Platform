package com.fxs.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.domain.City;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseCodeType;
import com.fxs.platform.security.core.support.SimpleResponse;
import com.fxs.platform.service.CityService;

/**
 * 
 * @author Charles
 *
 */
@RestController
@RequestMapping("/city")
public class CityController {
	
	LocaleMessageSourceService localeMessageSourceService;
	@Autowired
	CityService cityService;

	@GetMapping("/{id}")
	public SimpleResponse<List<City>> getProvinces(@PathVariable String id) {
		List<City> list = cityService.findProvinceByParentCityId("10105");

		return new SimpleResponse<List<City>>(ResponseCodeType.ZERO.getValue(), 
				localeMessageSourceService.getMessage("fxs.platform.application.province")
				, list);
	}
}
