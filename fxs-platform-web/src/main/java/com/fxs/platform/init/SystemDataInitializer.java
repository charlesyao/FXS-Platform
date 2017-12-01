package com.fxs.platform.init;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;

/**
 * 
 *
 */
@Component
public class SystemDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	/**
	 * Collect all {@link DataInitializer}
	 */
	@Autowired(required = false)
	private List<DataInitializer> dataInitializers;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (CollectionUtils.isNotEmpty(dataInitializers)) {

			dataInitializers.sort((initor1, initor2) -> {
				return initor1.getIndex().compareTo(initor2.getIndex());
			});

			dataInitializers.stream().forEach(dataInitializer -> {
				try {
					dataInitializer.init();
				} catch (Exception e) {
					logger.info(localeMessageSourceService.getMessage("fxs.platform.application.data.initializer.fali",
							new Object[] { dataInitializer.getClass().getSimpleName() }), e);
				}
			});
		}
	}

}
