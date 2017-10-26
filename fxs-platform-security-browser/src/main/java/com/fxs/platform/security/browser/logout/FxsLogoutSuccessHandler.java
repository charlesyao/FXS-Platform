package com.fxs.platform.security.browser.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseCodeType;
import com.fxs.platform.security.core.support.SimpleResponse;

/**
 * Default logout processor
 * If the property fxs.security.browser.signOutUrl has been set, system will redirect to the URL,
 * otherwise, system will return a json object
 * 
 * @author Charles
 *
 */
public class FxsLogoutSuccessHandler implements LogoutSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	public FxsLogoutSuccessHandler(String signOutUrl) {
		this.signOutUrl = signOutUrl;
	}

	private String signOutUrl;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		logger.info(
				localeMessageSourceService.getMessage("fxs.platform.browser.logout.success-logout"));

		if (StringUtils.isBlank(signOutUrl)) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter()
					.write(objectMapper.writeValueAsString(new SimpleResponse<Object[]>(ResponseCodeType.ZERO.getValue(),
							localeMessageSourceService.getMessage("fxs.platform.browser.logout.success-logout"), new Object[] {})));
		} else {
			response.sendRedirect(signOutUrl + "?logout");
		}
	}
}
