package com.fxs.platform.repository.condition;

/**
 * 
 * @author Charles
 *
 */
public class CityCondition {
	// 城市ID
	public String[] cityId;

	// 上一级城市ID
	public String parentCityId;

	//城市级别
	public String level;

	public String[] getCityId() {
		return cityId;
	}

	public void setCityId(String[] cityId) {
		this.cityId = cityId;
	}

	public String getParentCityId() {
		return parentCityId;
	}

	public void setParentCityId(String parentCityId) {
		this.parentCityId = parentCityId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
