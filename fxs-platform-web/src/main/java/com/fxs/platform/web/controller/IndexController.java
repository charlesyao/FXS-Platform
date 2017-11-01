package com.fxs.platform.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.fxs.platform.repository.FalltypusRepository;
import com.fxs.platform.repository.condition.CityCondition;
import com.fxs.platform.service.CityService;
import com.fxs.platform.support.EnabledCitySettings;

/**
 * 
 * @author Charles
 *
 */
@Controller
public class IndexController {
	@Autowired
	FalltypusRepository falltypusRepository;

	@Autowired
	CityService cityService;

	@Autowired
	EnabledCitySettings enabledCitySettings;

	@GetMapping("/")
	public String index(ModelMap map) {
		CityCondition condition = new CityCondition();
		condition.setCityId(StringUtils.splitByWholeSeparatorPreserveAllTokens(enabledCitySettings.getCityId(), ","));
		map.put("falltypusdata", falltypusRepository.findAll());
		map.put("enabledCity", cityService.query(condition));
		return "formsubmit";
	}
}
