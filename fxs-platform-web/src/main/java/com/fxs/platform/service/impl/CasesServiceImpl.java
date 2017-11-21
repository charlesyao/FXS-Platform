package com.fxs.platform.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.Reservation;
import com.fxs.platform.domain.User;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CasesRepository;
import com.fxs.platform.repository.ReservationRepository;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.CasesService;
import com.fxs.platform.utils.CaseStatus;

@Service
@Transactional
public class CasesServiceImpl implements CasesService {

	@Autowired
    HttpSession session;
	
	@Autowired
	CasesRepository caseRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	User user = (User)(session.getAttribute("userInfo"));

	@Override
	public Reservation create(Reservation reservation) {

		return reservationRepository.save(reservation);
	}
	
	@Override
	public Cases create(Cases cases) {
		cases.setStatus(CaseStatus.NEW.getStatus());
		return caseRepository.save(cases);
	}

	@Override
	public List<CasesDto> findAll() {
		return QueryResultConverter.convert(caseRepository.findAll(), CasesDto.class);
	}

	@Override
	public List<CasesDto> findByStatus(String status) {
		
		return caseRepository.findByStatus(String.valueOf(user.getId()), status);
	}
	
	@Override
	public List<CasesDto> findByType(String caseType) {

		return caseRepository.findByType(String.valueOf(user.getId()), caseType);
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
