package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Consultation;
import com.fxs.platform.dto.ConsultationDto;
import com.fxs.platform.repository.ConsultationRepository;
import com.fxs.platform.repository.condition.ConsultationCondition;
import com.fxs.platform.repository.specification.ConsultationSpecification;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.ConsultationService;

/**
 * 
 * @author Charles
 *
 */
@Service
public class ConsultationServiceImpl implements ConsultationService {

	@Autowired
	ConsultationRepository consultationRepository;

	@Override
	public Consultation create(Consultation consultation) {

		return consultationRepository.save(consultation);
	}

	@Override
	public Page<ConsultationDto> query(ConsultationCondition condition, Pageable pageable) {
		Page<Consultation> lawsuit = consultationRepository.findAll(new ConsultationSpecification(condition), pageable);
		return QueryResultConverter.convert(lawsuit, ConsultationDto.class, pageable);
	}

	@Override
	public Consultation update(String consultationId, Consultation consultation) {
		Consultation c = consultationRepository.findOne(consultationId);
		if (!ObjectUtils.isEmpty(c)) {
			BeanUtils.copyProperties(consultation, c);
		}

		return create(c);
	}

	@Override
	public List<ConsultationDto> findAll() {
		return QueryResultConverter.convert(consultationRepository.findAll(), ConsultationDto.class);
	}

}