package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.Reservation;
import com.fxs.platform.dto.CasesDto;

public interface CasesService {
	
	Reservation create(Reservation reservation);
	
	Cases create(Cases cases);

	//List<CasesDto> findByTypeAndSubType(String caseType, String subType);
	
	List<CasesDto> findAll();
	
	List<CasesDto> findByStatus(String status);
	
	Cases findByCaseId(String caseId);
	
	Cases update(String caseId, Cases cases);

	//我的免费咨询和我的法律咨询
	List<CasesDto> findByType(String caseType);
	
	List<Reservation> findAllReservation();
}
