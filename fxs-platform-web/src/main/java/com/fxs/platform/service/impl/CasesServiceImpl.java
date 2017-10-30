package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CasesRepository;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.CasesService;

/**
 * 
 * @author Charles
 *
 */
@Service
public class CasesServiceImpl implements CasesService {

	@Autowired
	CasesRepository caseRepository;

	@Override
	public Cases create(Cases cases) {
		return caseRepository.save(cases);
	}

	@Override
	public List<CasesDto> query(String caseType, String subType) {
		List<Cases> cases = caseRepository.findByTypeAndSubType(caseType, subType);
		return QueryResultConverter.convert(cases, CasesDto.class);
	}
}
