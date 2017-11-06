package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.City;

@Repository
public interface CityRepository extends FxsRepository<City> {
	@Query("SELECT o FROM City o WHERE o.parentCityId=?1")
	List<City> findProvinceByParentCityId(String parentCityId);

	@Query("SELECT o FROM City o WHERE o.level=1")
	List<City> findFirstLevelCities(Sort sort);

	@Query("SELECT o FROM City o WHERE o.level=1 AND o.cityId in ?1")
	List<City> findFirstLevelCityByIds(String[] ids, Sort sort);
}
