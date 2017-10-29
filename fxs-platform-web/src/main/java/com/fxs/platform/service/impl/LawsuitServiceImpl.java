package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.Lawsuit;
import com.fxs.platform.dto.LawsuitDto;
import com.fxs.platform.repository.LawsuitRepository;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.LawsuitService;

/**
 * 
 * @author Charles
 *
 */
@Service
public class LawsuitServiceImpl implements LawsuitService {

	@Autowired
	LawsuitRepository lawsuitRepository;

	@Override
	public Lawsuit create(Lawsuit lawsuit) {
		return lawsuitRepository.save(lawsuit);
	}

	@Override
	public List<LawsuitDto> query(String advisoryType) {
		List<Lawsuit> lawsuit = lawsuitRepository.findByType(advisoryType);
		return QueryResultConverter.convert(lawsuit, LawsuitDto.class);
	}
}
