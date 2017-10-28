package com.fxs.platform.async.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fxs.platform.service.CityService;
import com.fxs.platform.utils.CityDataHelper;
import com.fxs.platform.utils.CityDataType;

/**
 * 
 * @author Charles
 *
 */
@Component
public class GetCityDataTask implements ApplicationListener<ApplicationReadyEvent> {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CityService cityService;

	@Override
	@Async
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		try {
			logger.info("========================== Start Loading City Data into local DB ==========================");
			if(cityService.count() == 0) {
				CityDataHelper helper = new CityDataHelper();
				helper.getCitys(cityService, 1, "", CityDataType.CITY.getUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
