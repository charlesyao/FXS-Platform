package com.fxs.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.dto.CityDto;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	CityService cityService;

	/**
	 *
	 * @return
	 */
	@GetMapping
	public ResponseMessage<List<CityDto>> getFirstLevelCities() {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.city"),
				cityService.findFirstLevelCities());
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseMessage<List<CityDto>> getProvinces(@PathVariable String id) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.province"),
				cityService.findProvinceByParentCityId(id));
	}

	/**
	 * 查询一级城市和与之对应的省份
	 *
	 * @param condition
	 * @return
	 */
	/*
	@GetMapping
	public ResponseMessage<List<CityDto>> query(CityCondition condition) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.city"),
				cityService.query(condition));
	}
	*/
}
