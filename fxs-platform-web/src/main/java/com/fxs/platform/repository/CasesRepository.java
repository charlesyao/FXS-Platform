package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Cases;

@Repository
public interface CasesRepository extends FxsRepository<Cases> {
	List<Cases> findByTypeAndSubType(String caseType, String subType);

	List<Cases> findByStatus(String status);
	
	@Query("SELECT o FROM Cases o where o.id=?1")
	Cases findOne(String caseId);
}
