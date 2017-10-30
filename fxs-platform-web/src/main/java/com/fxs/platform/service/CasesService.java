package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.dto.CasesDto;

/**
 * 
 * @author Charles
 *
 */
public interface CasesService {
	Cases create(Cases cases);

	List<CasesDto> findByTypeAndSubType(String caseType, String subType);
	
	List<CasesDto> findAll();
	
	List<CasesDto> findByStatus(String status);
	
	Cases findByCaseId(String caseId);
	
	Cases update(String caseId, Cases cases);
}
