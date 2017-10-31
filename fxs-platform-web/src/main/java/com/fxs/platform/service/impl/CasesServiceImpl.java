package com.fxs.platform.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CasesRepository;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.CasesService;
import com.fxs.platform.utils.CaseStatus;

/**
 * 
 * @author Charles
 *
 */
@Service
@Transactional
public class CasesServiceImpl implements CasesService {

	@Autowired
	CasesRepository caseRepository;

	@Override
	public Cases create(Cases cases) {
		cases.setStatus(CaseStatus.NEW.getStatus());
		return caseRepository.save(cases);
	}

	@Override
	public List<CasesDto> findByTypeAndSubType(String caseType, String subType) {
		List<Cases> cases = caseRepository.findByTypeAndSubType(caseType, subType);
		return QueryResultConverter.convert(cases, CasesDto.class);
	}

	@Override
	public List<CasesDto> findAll() {
		return QueryResultConverter.convert(caseRepository.findAll(), CasesDto.class);
	}

	@Override
	public List<CasesDto> findByStatus(String status) {
		return QueryResultConverter.convert(caseRepository.findByStatus(status), CasesDto.class);
	}

	@Override
	public Cases findByCaseId(String caseId) {
		return caseRepository.findOne(caseId);
	}

	@Override
	public Cases update(String caseId, Cases cases) {
		Cases c = caseRepository.findOne(caseId);

		if (ObjectUtils.isEmpty(c)) {
			return null;
		}

		BeanUtils.copyProperties(cases, c);
		return caseRepository.saveAndFlush(c);
	}
}
