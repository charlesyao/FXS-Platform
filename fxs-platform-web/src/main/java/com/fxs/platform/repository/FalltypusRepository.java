package com.fxs.platform.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Falltypus;

/**
 * @author Charles
 *
 */
@Repository
public interface FalltypusRepository extends FxsRepository<Falltypus> {
	
	@Query("SELECT o FROM Falltypus o")
	@Cacheable(value="__falltypus-data__") 
	List<Falltypus> findAll();
}
