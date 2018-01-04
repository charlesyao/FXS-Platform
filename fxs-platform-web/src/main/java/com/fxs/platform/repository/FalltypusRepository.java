package com.fxs.platform.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Falltypus;

@Repository
public interface FalltypusRepository extends FxsRepository<Falltypus> {
	
	@Query("SELECT o FROM Falltypus o")
	List<Falltypus> findAll();

	@Query("SELECT o FROM Falltypus o WHERE o.parentTypeId=''")
	List<Falltypus> findFirstLevelFalltypus();
	
	@Query("SELECT o FROM Falltypus o WHERE o.parentTypeId !=''")
	List<Falltypus> findSubFalltypus();

	@Query("SELECT o FROM Falltypus o WHERE o.parentTypeId=?1")
	List<Falltypus> findSubFalltypusByParentId(String falltypusId);
	
	@Query("SELECT o FROM Falltypus o WHERE o.id=?1")
	Falltypus findById(String falltypusId);
	
	@Modifying
	@Query("DELETE FROM Falltypus o where o.id = ?1")
	int delete(String fId);
}
