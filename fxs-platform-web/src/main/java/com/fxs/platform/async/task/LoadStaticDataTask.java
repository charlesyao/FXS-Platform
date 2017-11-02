package com.fxs.platform.async.task;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fxs.platform.dto.CityDto;
import com.fxs.platform.dto.FalltypusDto;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.CityService;
import com.fxs.platform.service.FalltypusService;
import com.fxs.platform.support.EnabledCitySettings;

/**
 * 
 * @author Charles
 *
 */
@Component
@Order(2)
public class LoadStaticDataTask implements ApplicationListener<ApplicationReadyEvent> {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	FalltypusService falltypusService;

	@Autowired
	CityService cityService;

	@Autowired
	EnabledCitySettings enabledCitySettings;

	@Override
	@Async
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		try {
			loadFalltypusData();
			loadCityData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadFalltypusData() {
		List<FalltypusDto> falltypusList = QueryResultConverter.convert(
				falltypusService.findFirstLevelFalltypus(), FalltypusDto.class);

		for (FalltypusDto falltypusDto : falltypusList) {
			falltypusService.findSubFalltypusByParentId(falltypusDto.getTypeId());
		}
	}

	private void loadCityData() {
		String idStr = enabledCitySettings.getCityId();
		List<CityDto> level1Cities = cityService.findFirstLevelCities();

		if (StringUtils.isNotBlank(idStr)) {
			String[] enabledCityIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(idStr, ",");

			for (String id : enabledCityIds) {
				cityService.findProvinceByParentCityId(id);
			}
		} else {
			for (CityDto cityDto : level1Cities) {
				cityService.findProvinceByParentCityId(cityDto.getCityId());
			}
		}
	}
}