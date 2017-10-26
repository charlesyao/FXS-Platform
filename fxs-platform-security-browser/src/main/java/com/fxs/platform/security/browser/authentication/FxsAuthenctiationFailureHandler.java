package com.fxs.platform.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.properties.LoginResponseType;
import com.fxs.platform.security.core.properties.SecurityProperties;
import com.fxs.platform.security.core.support.ResponseCodeType;
import com.fxs.platform.security.core.support.SimpleResponse;

/**
 * Failure login processor
 * 
 * @author Charles
 *
 */
@Component("fxsAuthenctiationFailureHandler")
public class FxsAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info(
				localeMessageSourceService.getMessage("fxs.platform.browser.login.faiure-login", 
						new Object[] {exception.getMessage()}));

		if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getSignInResponseType())) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(
					new SimpleResponse<Object[]>(ResponseCodeType.MINUS_ONE.getValue(), exception.getMessage(), 
							new Object[] {})));
		} else {
			requestCache.removeRequest(request, response);
			setDefaultFailureUrl(securityProperties.getBrowser().getSignInPage() + "?error");
			super.onAuthenticationFailure(request, response, exception);
		}
	}
}
