package com.fxs.platform.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.fxs.platform.security.core.properties.SecurityProperties;

/**
 * 
 * @author Charles
 *
 */
public class FxsExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

	public FxsExpiredSessionStrategy(SecurityProperties securityPropertie) {
		super(securityPropertie);
	}

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		onSessionInvalid(event.getRequest(), event.getResponse());
	}

	@Override
	protected boolean isConcurrency() {
		return true;
	}
}
