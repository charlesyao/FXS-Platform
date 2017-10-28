package com.fxs.platform.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.City;

/**
 * @author Charles
 *
 */
@Repository
@CacheConfig(cacheNames = "_citydata_")
public interface CityRepository extends FxsRepository<City> {
	
	@Query("SELECT o FROM City o WHERE o.parentCityId=?1")
	List<City> findProvinceByParentCityId(String parentCityId);
}
