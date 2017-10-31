package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Consultation;
import com.fxs.platform.repository.ConsultationRepository;
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
	public List<Consultation> findByType(String consultationType) {
		return consultationRepository.findByType(consultationType);
	}

	@Override
	public List<Consultation> findByStatus(String consultaionStatus) {
		return consultationRepository.findByStatus(consultaionStatus);
	}

	@Override
	public Consultation findByConsultationId(String consultationId) {

		return consultationRepository.findOne(consultationId);
	}

	@Override
	public List<Consultation> findAll() {
		return consultationRepository.findAll();
	}

	@Override
	public Consultation update(String consultationId, Consultation consultation) {
		Consultation c = consultationRepository.findOne(consultationId);
		if (!ObjectUtils.isEmpty(c)) {
			BeanUtils.copyProperties(consultation, c);
		}

		return create(c);
	}
}
