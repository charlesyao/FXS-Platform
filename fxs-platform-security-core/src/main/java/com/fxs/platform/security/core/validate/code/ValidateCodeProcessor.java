package com.fxs.platform.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * ValidateCodeProcessor
 * 
 * @author Charles
 *
 */
public interface ValidateCodeProcessor {

	void create(ServletWebRequest request) throws Exception;

	void validate(ServletWebRequest servletWebRequest);

}
