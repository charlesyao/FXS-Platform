package com.fxs.platform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fxs.platform.domain.Consultation;
import com.fxs.platform.dto.ConsultationDto;
import com.fxs.platform.repository.condition.ConsultationCondition;

/**
 * 
 * @author Charles
 *
 */
public interface ConsultationService {

	Consultation create(Consultation consultation);

	Page<ConsultationDto> query(ConsultationCondition condition, Pageable pageable);

	Consultation update(String consultationId, Consultation consultation);
}
