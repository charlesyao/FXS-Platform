package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Cases;

@Repository
public interface CasesRepository extends FxsRepository<Cases> {
	//List<Cases> findByTypeAndSubType(String caseType, String subType);

	@Query("SELECT o FROM Cases o where o.caseType=?1  AND o.feedbackCount<=10 AND o.status != '5' ORDER BY o.createAt DESC")
	Page<Cases> findAllCases(String type, Pageable pageable);
	
	@Query("SELECT o FROM Cases o where o.userId=?1 AND o.status=?2")
	List<Cases> findByStatus(String userId, String status);
	
	@Query("SELECT o FROM Cases o where o.id=?1")
	Cases findOne(String caseId);
	
	@Query("SELECT o FROM Cases o where o.userId=?1 AND o.caseType=?2 ORDER BY createAt DESC")
	List<Cases> findByType(String userId, String caseType);
	
	@Query("UPDATE Cases o SET o.status =?1 where o.id=?2")
	@Modifying
	void updateStatus(String statusCode, String caseTId);
	
	@Query("SELECT o FROM Cases o where o.status != '5' AND o.expiredDate=CURDATE()")
	List<Cases> findByStatus();
}
