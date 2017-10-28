package com.fxs.platform.utils;

/**
 * 
 * @author Charles
 *
 */
public enum CityDataType {
	CITY("http://www.weather.com.cn/data/city3jdata/china.html"), 
	PROVINCE("http://www.weather.com.cn/data/city3jdata/provshi/"),
	STATION("http://www.weather.com.cn/data/city3jdata/station/");

	private String url;

	private CityDataType(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
