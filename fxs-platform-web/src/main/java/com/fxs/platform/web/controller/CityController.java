package com.fxs.platform.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.dto.CityDto;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.CityService;
import com.fxs.platform.utils.SystemConstants;

@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	CityService cityService;
	
	@Autowired
	HttpSession session;

	/**
	 * 获取配置的第一级城市,如果没有配置，默认获取全国所有的一级城市
	 * @return
	 */
	@GetMapping
	public ResponseMessage<String> getFirstLevelCities(ModelMap map) {
		List<CityDto> cities = cityService.findFirstLevelCities();
		
		String level1CityListHTML = "";
		
		for (CityDto cityDto : cities) {
			level1CityListHTML += "<li id='" + cityDto.getCityId() + "'><a href=''>" + cityDto.getName() + "</a></li>";
		}
		
		return Result.success(level1CityListHTML + "<div class='clearboth'></div>");
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseMessage<String> getProvinces(@PathVariable String id) {
		
		session.setAttribute(SystemConstants.LEVEL_1_CITY, id);
		
		List<CityDto> cities = cityService.findProvinceByParentCityId(id);
		
	    String level2CityListHTML = "";
		
		for (CityDto cityDto : cities) {
			level2CityListHTML += "<li id='" + cityDto.getCityId() + "'><a href=''>" + cityDto.getName() + "</a></li>";
		}
		
		return Result.success(level2CityListHTML + "<div class='clearboth'></div>");
	}
	
	@GetMapping("/autosave/{id}")
	public ResponseMessage<String> autosaveLevel2(@PathVariable String id) {
		
		session.setAttribute(SystemConstants.LEVEL_2_CITY, id);
		
		return Result.success("1");
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
