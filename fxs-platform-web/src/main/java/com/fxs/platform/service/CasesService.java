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

	List<CasesDto> query(String caseType, String subType);
}
