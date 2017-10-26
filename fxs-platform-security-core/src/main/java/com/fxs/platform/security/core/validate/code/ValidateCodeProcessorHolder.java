package com.fxs.platform.security.core.validate.code;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;

/**
 * 校验码处理器管理器
 * 
 * @author Charles
 *
 */
@Component
public class ValidateCodeProcessorHolder {

	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;
	
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}

	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidateCodeException(
					localeMessageSourceService.getMessage("fxs.platform.core.validation.code.processor-not-exist", new Object[] {name}));
		}
		return processor;
	}

}
