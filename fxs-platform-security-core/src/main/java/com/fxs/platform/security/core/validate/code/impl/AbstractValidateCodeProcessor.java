package com.fxs.platform.security.core.validate.code.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.validate.code.ValidateCode;
import com.fxs.platform.security.core.validate.code.ValidateCodeException;
import com.fxs.platform.security.core.validate.code.ValidateCodeGenerator;
import com.fxs.platform.security.core.validate.code.ValidateCodeProcessor;
import com.fxs.platform.security.core.validate.code.ValidateCodeRepository;
import com.fxs.platform.security.core.validate.code.ValidateCodeType;

/**
 * Abstract validate code process
 * 
 * @author Charles
 *
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

	/**
	 * Collect all implementation of  {@link ValidateCodeGenerator}
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	@Autowired
	private ValidateCodeRepository validateCodeRepository;
	
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;
	
	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}

	/**
	 * create validate code
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type = getValidateCodeType(request).toString().toLowerCase();
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException(
					localeMessageSourceService.getMessage("fxs.platform.browser.validation.code.generator.not-exist", new Object[] {generatorName}));
		}
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * Store validate code
	 * 
	 * @param request
	 * @param validateCode
	 */
	private void save(ServletWebRequest request, C validateCode) {
		ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
		validateCodeRepository.save(request, code, getValidateCodeType(request));
	}

	/**
	 * Send validate code. It should be implemented by their sub-class
	 * 
	 * @param request
	 * @param validateCode
	 * @throws Exception
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

	/**
	 * get validate code type by URL
	 * code/image, code/sms
	 * 
	 * @param request
	 * @return
	 */
	private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
		return ValidateCodeType.valueOf(type.toUpperCase());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validate(ServletWebRequest request) {

		ValidateCodeType codeType = getValidateCodeType(request);

		C codeInSession = (C) validateCodeRepository.get(request, codeType);

		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
					codeType.getParamNameOnValidate());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException(
					localeMessageSourceService.getMessage("fxs.platform.browser.validation.code.failure-get"));
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(
					localeMessageSourceService.getMessage("fxs.platform.browser.validation.code.is-blank", 
							new Object[] {transferCodeType(codeType)}));
		}

		if (codeInSession == null) {
			throw new ValidateCodeException(
					localeMessageSourceService.getMessage("fxs.platform.browser.validation.code.not-exist", 
							new Object[] {transferCodeType(codeType)}));
		}

		if (codeInSession.isExpried()) {
			validateCodeRepository.remove(request, codeType);
			throw new ValidateCodeException(
					localeMessageSourceService.getMessage("fxs.platform.browser.validation.code.is-expired", 
							new Object[] {transferCodeType(codeType)}));
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(
					localeMessageSourceService.getMessage("fxs.platform.browser.validation.code.not-match", 
							new Object[] {transferCodeType(codeType)}));
		}

		validateCodeRepository.remove(request, codeType);
	}
	
	private String transferCodeType(ValidateCodeType codeType) {
		return ValidateCodeType.IMAGE.equals(codeType) 
				? localeMessageSourceService.getMessage("fxs.platform.browser.validation.code.type-image") 
				: localeMessageSourceService.getMessage("fxs.platform.browser.validation.code.type-sms");
	}
}
