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
import com.fxs.platform.service.RoleService;
import com.fxs.platform.support.EnabledCitySettings;

/**
 *异步任务： 加载所有系统静态数据，包括案件类型，城市数据，用户角色 
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
	RoleService roleService;

	@Autowired
	EnabledCitySettings enabledCitySettings;

	@Override
	@Async
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		try {
			loadFalltypusData();
			loadCityData();
			loadRoleData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadFalltypusData() {
		List<FalltypusDto> falltypusList = QueryResultConverter.convert(falltypusService.findFirstLevelFalltypus(),
				FalltypusDto.class);

		for (FalltypusDto falltypusDto : falltypusList) {
			falltypusService.findSubFalltypusByParentId(String.valueOf(falltypusDto.getId()));
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

	private void loadRoleData() {
		roleService.findAll();
	}
}
