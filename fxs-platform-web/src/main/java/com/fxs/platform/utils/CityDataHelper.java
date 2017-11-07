package com.fxs.platform.utils;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fxs.platform.domain.City;
import com.fxs.platform.service.CityService;

public class CityDataHelper {
	Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("deprecation")
	public void getCitys(CityService cityService, int level, String pid, String url) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response = null;

		String citys = null;
		String[] citys1Array = null;
		int index = 0;
		String cityName = null;
		String cityID = null;

		try {
			response = client.execute(request);
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				citys = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				if (citys != null && citys.trim().length() > 0) {
					citys1Array = citys.substring(1, citys.length() - 1).split(",");
					for (int i = 0; i < citys1Array.length; i++) {
						index = citys1Array[i].indexOf(":");
						// 当前城市的ID需要用上一级城市的ID拼接出来，但是有个别的三级城市直接是最终的ID了
						cityID = citys1Array[i].substring(1, index - 1);
						if (level != 3 || cityID.length() < 9) {
							cityID = pid + cityID;
						}

						cityName = citys1Array[i].substring(index + 2, citys1Array[i].length() - 1);

						// 插入数据库
						City city = new City();
						city.setName(cityName);
						city.setCityId(cityID);
						city.setLevel(Integer.toString(level));
						city.setParentCityId(pid);

						cityService.save(city);

						// 递归下一级列表
						if (level == 1) {
							// 获取二级列表
							getCitys(cityService, 2, cityID, CityDataType.PROVINCE.getUrl() + cityID + ".html");
						} else if (level == 2) { 
							// 获取三级列表 
							getCitys(cityService, 3, cityID, CityDataType.STATION.getUrl() + cityID + ".html"); 
						} else if (level == 3) {
							continue;
						}
					}
				}
			} else {
				logger.error("Unexpected response status: " + status);
			}
		} catch (IOException | UnsupportedOperationException e) {
			e.printStackTrace();
		} finally {
			if (null != response) {
				try {
					response.close();
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
