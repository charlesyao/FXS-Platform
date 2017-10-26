package com.fxs.platform.security.browser;

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
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.security.core.properties.SecurityProperties;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.properties.SecurityConstants;
import com.fxs.platform.security.core.support.ResponseCodeType;
import com.fxs.platform.security.core.support.SimpleResponse;

/**
 * 
 * @author Charles
 *
 */
@RestController
public class BrowserSecurityController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private RequestCache requestCache = new HttpSessionRequestCache();

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	/**
	 * Pre-handling all requests which needs authentication
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse<Object[]> requireAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info(
					localeMessageSourceService.getMessage("fxs.platform.browser.session.trigger-redirect", new Object[] {targetUrl}));
			if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getSignInPage());
			}
		}

		return new SimpleResponse<Object[]>(ResponseCodeType.MINUS_ONE.getValue(), 
				localeMessageSourceService.getMessage("fxs.platform.browser.session.need-authentication"),
				new Object[] {}
				);
	}
}
