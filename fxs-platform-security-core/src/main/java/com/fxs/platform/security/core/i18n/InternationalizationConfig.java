package com.fxs.platform.security.core.i18n;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fxs.platform.security.core.properties.SecurityProperties;
import com.google.common.base.Charsets;

/**
 * 
 * @author Charles
 *
 */
@Configuration
public class InternationalizationConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(LocaleContextHolder.getLocale());
		return slr;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		String baseNames = securityProperties.getMessage().getBaseName();
		
		if(StringUtils.isEmpty(baseNames)) {
			messageSource.setBasenames("classpath:i18n/messages-core", "classpath:i18n/messages-browser");
		} else {
			baseNames = baseNames.concat(",").concat("classpath:i18n/messages-core,classpath:i18n/messages-browser");
			messageSource.setBasenames(StringUtils.splitByWholeSeparatorPreserveAllTokens(baseNames, ","));
		}
		//messageSource.setBasenames("classpath:i18n/messages");
		messageSource.setCacheSeconds(3600);
		messageSource.setDefaultEncoding(Charsets.UTF_8.name());
		return messageSource;
	}
}
