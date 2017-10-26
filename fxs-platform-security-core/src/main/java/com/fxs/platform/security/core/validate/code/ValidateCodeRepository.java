package com.fxs.platform.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * ValidateCodeRepository
 * 
 * @author Charles
 *
 */
public interface ValidateCodeRepository {

	void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

	ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

	void remove(ServletWebRequest request, ValidateCodeType codeType);

}
