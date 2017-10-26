package com.fxs.platform.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * ValidateCodeGenerator
 * 
 * @author Charles
 *
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);

}
