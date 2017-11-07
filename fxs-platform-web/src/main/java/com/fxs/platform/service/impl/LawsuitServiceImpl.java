package com.fxs.platform.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Lawsuit;
import com.fxs.platform.dto.LawsuitDto;
import com.fxs.platform.repository.LawsuitRepository;
import com.fxs.platform.repository.condition.LawsuitCondition;
import com.fxs.platform.repository.specification.LawsuitSpecification;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.LawsuitService;

@Service
public class LawsuitServiceImpl implements LawsuitService {

	@Autowired
	LawsuitRepository lawsuitRepository;

	@Override
	public Lawsuit create(Lawsuit lawsuit) {

		return lawsuitRepository.save(lawsuit);
	}
	
	@Override
	public Page<LawsuitDto> query(LawsuitCondition condition, Pageable pageable) {
		Page<Lawsuit> lawsuit = lawsuitRepository.findAll(new LawsuitSpecification(condition), pageable);
		return QueryResultConverter.convert(lawsuit, LawsuitDto.class, pageable);
	}

	@Override
	public Lawsuit update(String lawsuitId, Lawsuit lawsuit) {
		Lawsuit l = lawsuitRepository.findOne(lawsuitId);
		if (!ObjectUtils.isEmpty(l)) {
			BeanUtils.copyProperties(lawsuit, l);
		}

		return create(l);
	}
	
}
