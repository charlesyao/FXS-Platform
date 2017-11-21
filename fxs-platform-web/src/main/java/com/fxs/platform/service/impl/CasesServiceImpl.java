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
import com.fxs.platform.utils.SystemConstants;

@Service
@Transactional
public class CasesServiceImpl implements CasesService {

	@Autowired
    HttpSession session;
	
	@Autowired
	CasesRepository caseRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	HttpSession httpSession;
	
	@Override
	public Reservation create(Reservation reservation) {
		User user = (User)(session.getAttribute("userInfo"));
		if (!ObjectUtils.isEmpty(user)) {
			reservation.setUserId(String.valueOf(user.getId()));
		} else {
			reservation.setUserId("匿名用户");
		}
		
		return reservationRepository.save(reservation);
	}
	
	@Override
	public Cases create(Cases cases) {
		User user = (User)(session.getAttribute("userInfo"));
		String parentFalltypusType = String.valueOf(session.getAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE));
		String subTypeFalltypusType = String.valueOf(session.getAttribute(SystemConstants.FALLTYPUS_LEVEL2_TYPE));
		
		cases.setParentType(parentFalltypusType);
		cases.setSubType(subTypeFalltypusType);
		cases.setUserId(String.valueOf(user.getId()));
		cases.setStatus(CaseStatus.NEW.getStatus());
		
		return caseRepository.save(cases);
	}

	@Override
	public List<CasesDto> findAll() {
		return QueryResultConverter.convert(caseRepository.findAll(), CasesDto.class);
	}

	@Override
	public List<CasesDto> findByStatus(String status) {
		User user = (User)(session.getAttribute("userInfo"));
		return caseRepository.findByStatus(String.valueOf(user.getId()), status);
	}
	
	@Override
	public List<CasesDto> findByType(String caseType) {
		User user = (User)(session.getAttribute("userInfo"));
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

	@Override
	public List<Reservation> findAllReservation() {
		User user = (User)(session.getAttribute("userInfo"));
		return reservationRepository.queryAll(String.valueOf(user.getId()));
	}
}
