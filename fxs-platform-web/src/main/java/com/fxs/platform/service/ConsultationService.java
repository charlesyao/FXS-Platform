package com.fxs.platform.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fxs.platform.domain.Reservation;
import com.fxs.platform.dto.ConsultationDto;
import com.fxs.platform.repository.condition.ConsultationCondition;

public interface ConsultationService {

	Reservation create(Reservation consultation);
	
	List<ConsultationDto> findAll();

	Page<ConsultationDto> query(ConsultationCondition condition, Pageable pageable);

	Reservation update(String consultationId, Reservation consultation);
}
