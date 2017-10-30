package com.fxs.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.domain.City;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.service.CityService;
import com.fxs.platform.web.controller.support.ResponseMessage;
import com.fxs.platform.web.controller.support.Result;

/**
 * 
 * @author Charles
 *
 */
@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	CityService cityService;

	@GetMapping
	public ResponseMessage<List<City>> getFirstLevelCities() {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.city"),
				cityService.findFirstLevelCities());
	}

	@GetMapping("/{id}")
	public ResponseMessage<List<City>> getProvinces(@PathVariable String id) {
		return Result.success(
				localeMessageSourceService.getMessage("fxs.platform.application.province"),
				cityService.findProvinceByParentCityId(id));
	}
}
