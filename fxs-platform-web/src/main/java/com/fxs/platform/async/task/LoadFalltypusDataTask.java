package com.fxs.platform.async.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fxs.platform.repository.FalltypusRepository;

/**
 * 
 * @author Charles
 *
 */
@Component
public class LoadFalltypusDataTask implements ApplicationListener<ApplicationReadyEvent> {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	FalltypusRepository falltypusRepository;

	@Override
	@Async
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		try {
			falltypusRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
