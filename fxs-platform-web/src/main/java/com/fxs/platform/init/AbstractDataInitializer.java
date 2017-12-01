package com.fxs.platform.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;

/**
 *抽象初始化器 
 *
 */
public abstract class AbstractDataInitializer implements DataInitializer {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;
	
	@Override
	@Transactional
	public void init() throws Exception {
		if(isNeedInit()) {
			logger.info(
					localeMessageSourceService.getMessage("fxs.platform.application.data.initializer.start", new Object [] {getClass().getSimpleName()}));
			doInit();
			logger.info(
					localeMessageSourceService.getMessage("fxs.platform.application.data.initializer.end", new Object [] {getClass().getSimpleName()}));
		}
	}

	protected abstract void doInit() throws Exception;
	
	protected abstract boolean isNeedInit();

}
