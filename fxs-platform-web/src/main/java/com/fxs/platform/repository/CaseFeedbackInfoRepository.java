package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.CaseFeedbackInfo;

@Repository
public interface CaseFeedbackInfoRepository extends FxsRepository<CaseFeedbackInfo> {
	
	@Query("SELECT o FROM CaseFeedbackInfo o where o.caseId=?1 AND o.lawyerId=?2")
	List<CaseFeedbackInfo> findByCaseIdAndLawyerId(String caseId, String lawyerId);
	
	@Query("SELECT o FROM CaseFeedbackInfo o where o.caseId=?1")
	List<CaseFeedbackInfo> findByCaseIdAndLawyerId(String caseId);
	
	@Query("SELECT o FROM CaseFeedbackInfo o where o.caseId=?1 AND o.status='2'")
	List<CaseFeedbackInfo> findByCaseId(String caseId);
	
	@Query("SELECT o.caseId FROM CaseFeedbackInfo o where o.status=?1 AND o.lawyerId=?2")
	List<String> findCaseIdsByStatusAndUserId(String caseStatus, String lawyerId);


	@Query("UPDATE CaseFeedbackInfo o SET o.status =?1, o.approvedBy=?2 where o.id=?3")
	@Modifying
	void updateStatus(String status, String approvedBy, String feedbackId);
}
