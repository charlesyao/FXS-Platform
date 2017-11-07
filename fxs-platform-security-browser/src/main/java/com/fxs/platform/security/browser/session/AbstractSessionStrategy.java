package com.fxs.platform.security.browser.session;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.properties.SecurityProperties;
import com.fxs.platform.security.core.support.Result;

/**
 * Invalid session processor
 * 
 */
public class AbstractSessionStrategy {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	private String destinationUrl;

	private SecurityProperties securityPropertie;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private boolean createNewSession = true;

	private ObjectMapper objectMapper = new ObjectMapper();

	public AbstractSessionStrategy(SecurityProperties securityPropertie) {
		String invalidSessionUrl = securityPropertie.getBrowser().getSession().getSessionInvalidUrl();
		this.destinationUrl = invalidSessionUrl;
		this.securityPropertie = securityPropertie;
	}

	protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info(localeMessageSourceService.getMessage("fxs.platform.browser.session.is-invalid"));

		if (createNewSession) {
			request.getSession();
		}

		String sourceUrl = request.getRequestURI();
		String targetUrl;

		if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
			if (StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSignInPage())
					|| StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSignOutUrl())) {
				targetUrl = sourceUrl;
			} else {
				targetUrl = destinationUrl;
			}
			logger.info(
					localeMessageSourceService.getMessage("fxs.platform.browser.session.request-redirect-to", new Object[] {targetUrl}));
			redirectStrategy.sendRedirect(request, response, targetUrl);
		} else {
			Object result = buildResponseContent(request);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(result));
		}

	}

	protected Object buildResponseContent(HttpServletRequest request) {
		String message = localeMessageSourceService.getMessage("fxs.platform.browser.session.is-invalid");
		if (isConcurrency()) {
			message = localeMessageSourceService.getMessage("fxs.platform.browser.session.is-invalid-cause");
		}
		
		return Result.error(message);
	}

	/**
	 * session concurrency
	 * 
	 * @return
	 */
	protected boolean isConcurrency() {
		return false;
	}

	/**
	 * Determines whether a new session should be created before redirecting (to
	 * avoid possible looping issues where the same session ID is sent with the
	 * redirected request). Alternatively, ensure that the configured URL does not
	 * pass through the {@code SessionManagementFilter}.
	 *
	 * @param createNewSession
	 *            defaults to {@code true}.
	 */
	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}
}
