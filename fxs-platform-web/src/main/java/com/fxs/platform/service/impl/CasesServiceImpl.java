package com.fxs.platform.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.CaseFeedbackInfo;
import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.Reservation;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CaseFeedbackInfoRepository;
import com.fxs.platform.repository.CaseQuestionAnswerRelRepository;
import com.fxs.platform.repository.CasesRepository;
import com.fxs.platform.repository.CityRepository;
import com.fxs.platform.repository.FalltypusRepository;
import com.fxs.platform.repository.ReservationRepository;
import com.fxs.platform.repository.condition.CasesCondition;
import com.fxs.platform.repository.specification.CaseSpecification;
import com.fxs.platform.service.CasesService;
import com.fxs.platform.utils.CaseManager;
import com.fxs.platform.utils.SystemConstants;
import com.fxs.platform.utils.UserManager;

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
	CaseQuestionAnswerRelRepository caseQuestionAnswerRelRepository;
	
	@Autowired
	FalltypusRepository falltypusRepository;
	
	@Autowired
	CaseFeedbackInfoRepository caseFeedbackInfoRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	HttpSession httpSession;
	
	@Override
	public Reservation create(Reservation reservation) {
		reservation.setId(String.valueOf(new Random().nextInt(99999999)));
		reservation.setUserId(UserManager.getSessionUser(httpSession));

		return reservationRepository.save(reservation);
	}
	
	@Override
	public Cases create(Cases cases) {

		//cases.setId(String.valueOf(new Random().nextInt(99999999)));
		return caseRepository.save(cases);
	}

	@Override
	public Page<CasesDto> findAll(String type, Pageable pageable) {
		Page<Cases> cases = caseRepository.findAllCases(type, pageable);
		
		return CaseManager.caseWrapperPageable(cases, caseQuestionAnswerRelRepository, falltypusRepository, cityRepository, pageable);
	}

	@Override
	public List<Cases> findByStatus(String status) {
		
		return caseRepository.findByStatus(UserManager.getSessionUser(httpSession), status);
	}
	
	@Override
	public List<CasesDto> findByType(String caseType) {
		List<Cases> cases = caseRepository.findByType(UserManager.getSessionUser(httpSession), caseType.equals(SystemConstants.CASE_TYPE_CONSULTING) ? "0" : "1");
		
		if(!ObjectUtils.isEmpty(cases)) {
			return CaseManager.caseWrapper(cases, caseQuestionAnswerRelRepository, falltypusRepository, cityRepository);
		}
		
		return new ArrayList<CasesDto>();
	}
	
	@Override
	public Cases findByCaseId(String caseId) {
		return caseRepository.findOne(caseId);
	}

	@Override
	public Cases update(String caseId, CaseFeedbackInfo cases) {
		Cases c = caseRepository.findOne(caseId);

		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		
		if (ObjectUtils.isEmpty(c)) {
			return null;
		}

		if(! ObjectUtils.isEmpty(cases.getStatus())) {
			c.setStatus(cases.getStatus());
		}
		
		if (UserManager.isLawyer(roles)) {
			cases.setId(String.valueOf(new Random().nextInt(99999999)));
			cases.setCaseId(caseId);
			cases.setLawyerName(UserManager.getPrincipal());
			cases.setLawyerId(UserManager.getSessionUser(httpSession));
			
			if (c.getFeedbackCount() < SystemConstants.FEEDBACK_COUNT_TOTAL) {
				c.setFeedbackCount(c.getFeedbackCount() + 1);
				
				caseFeedbackInfoRepository.save(cases);
				
				return caseRepository.saveAndFlush(c);
			}
		} else {
			
			if(ObjectUtils.isEmpty(c.getAcceptedLawyerFeedback())) {
				c.setAcceptedLawyerFeedback(cases.getAcceptedLawyerFeedback());
			}
			
			return caseRepository.saveAndFlush(c);
		}
		
		return null;
	}

	@Override
	public Page<Reservation> findAllReservation(Pageable pageable) {
		return reservationRepository.queryAll(UserManager.getSessionUser(httpSession), pageable);
	}

	/**
	 * 更新案件状态
	 */
	@Override
	public void updateStatus(String statusCode, String caseId) {
		
		caseRepository.updateStatus(statusCode, caseId);
	}

	@Override
	public Page<CasesDto> query(CasesCondition condition, Pageable pageable) {

		if (UserManager.isUser(UserManager.getRoles())) {
			condition.setUserId(UserManager.getSessionUser(httpSession));
		}
		
		Page<Cases> pageableCases = caseRepository.findAll(new CaseSpecification(condition), pageable);
		
		return CaseManager.caseWrapperPageable(pageableCases, caseQuestionAnswerRelRepository, falltypusRepository, cityRepository, pageable);
	}

	@Override
	public Page<CasesDto> findAllWithDays(String type, Pageable pageable) {
		Page<Cases> cases = caseRepository.findAllWithDays(type, pageable);
		
		return CaseManager.caseWrapperPageable(cases, caseQuestionAnswerRelRepository, falltypusRepository, cityRepository, pageable);
	}
}
