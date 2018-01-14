package com.fxs.platform.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fxs.platform.domain.CaseFeedbackInfo;
import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.Reservation;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.condition.CasesCondition;

public interface CasesService {
	
	Reservation create(Reservation reservation);
	
	Cases create(Cases cases);

	//List<CasesDto> findByTypeAndSubType(String caseType, String subType);
	
	Page<CasesDto> findAll(String type, Pageable pageable);
	
	List<Cases> findByStatus(String status);
	
	Cases findByCaseId(String caseId);
	
	Cases update(String caseId, CaseFeedbackInfo cases);
	
	void updateStatus(String statusCode, String caseId);

	//我的免费咨询和我的法律咨询
	List<CasesDto> findByType(String caseType);
	
	Page<Reservation> findAllReservation(Pageable pageable);
	
	Page<CasesDto> query(CasesCondition condition, Pageable pageable);
	
	Page<CasesDto> findAllWithDays(String type, Pageable pageable);
}
