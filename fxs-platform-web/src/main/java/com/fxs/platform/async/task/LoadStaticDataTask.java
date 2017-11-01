package com.fxs.platform.async.task;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fxs.platform.repository.FalltypusRepository;
import com.fxs.platform.repository.condition.CityCondition;
import com.fxs.platform.service.CityService;
import com.fxs.platform.support.EnabledCitySettings;

/**
 * 
 * @author Charles
 *
 */
@Component
public class LoadStaticDataTask implements ApplicationListener<ApplicationReadyEvent> {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	FalltypusRepository falltypusRepository;

	@Autowired
	CityService cityService;

	@Autowired
	EnabledCitySettings enabledCitySettings;

	@Override
	@Async
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		try {
			// Load all falltypus data
			loadFalltypus();
			loadEnabledCities();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadFalltypus() {
		falltypusRepository.findAll();
	}

	private void loadEnabledCities() {
		CityCondition condition = new CityCondition();

		if (StringUtils.isNotBlank(enabledCitySettings.getCityId())) {

			String[] enabledCities = StringUtils.splitByWholeSeparatorPreserveAllTokens(enabledCitySettings.getCityId(), ",");
			condition.setCityId(enabledCities);
		}

		cityService.query(condition);
	}
}
