/**
 * 
 */
package com.fxs.platform.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Charles
 *
 */
@Component
@ConfigurationProperties(prefix = "fxs.constant")
public class EnabledCitySettings {
	private String cityId;

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

}
