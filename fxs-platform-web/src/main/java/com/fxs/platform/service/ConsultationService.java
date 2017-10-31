package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Consultation;

/**
 * 
 * @author Charles
 *
 */
public interface ConsultationService {

	Consultation create(Consultation consultation);

	List<Consultation> findByType(String consultationType);
	
	List<Consultation> findByStatus(String consultaionStatus);

	Consultation findByConsultationId(String consultationId);

	List<Consultation> findAll();

	Consultation update(String consultationId, Consultation consultation);

}
