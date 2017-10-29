package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.Advisory;
import com.fxs.platform.dto.AdvisoryDto;
import com.fxs.platform.repository.AdvisoryRepository;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.AdvisoryService;

/**
 * 
 * @author Charles
 *
 */
@Service
public class AdvisoryServiceImpl implements AdvisoryService {

	@Autowired
	AdvisoryRepository advisoryRepository;

	@Override
	public Advisory create(Advisory advisory) {
		return advisoryRepository.save(advisory);
	}

	@Override
	public List<AdvisoryDto> query(String advisoryType) {
		List<Advisory> advisory = advisoryRepository.findByType(advisoryType);
		return QueryResultConverter.convert(advisory, AdvisoryDto.class);
	}
}
