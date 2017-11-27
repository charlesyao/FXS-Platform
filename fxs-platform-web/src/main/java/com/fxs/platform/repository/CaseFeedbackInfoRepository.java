package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.CaseFeedbackInfo;

@Repository
public interface CaseFeedbackInfoRepository extends FxsRepository<CaseFeedbackInfo> {
	
	@Query("SELECT o FROM CaseFeedbackInfo o where o.caseId=?1")
	List<CaseFeedbackInfo> findByCaseId(String caseId);

}
