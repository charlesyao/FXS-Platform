package com.fxs.platform.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.Reservation;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CaseQuestionAnswerRelRepository;
import com.fxs.platform.repository.CasesRepository;
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
	HttpSession httpSession;
	
	@Override
	public Reservation create(Reservation reservation) {
		
		reservation.setUserId(UserManager.getSessionUser(httpSession));

		return reservationRepository.save(reservation);
	}
	
	@Override
	public Cases create(Cases cases) {
		/*User user = (User)(session.getAttribute(SystemConstants.USER_INFO));
		String parentFalltypusType = String.valueOf(session.getAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE));
		String subTypeFalltypusType = String.valueOf(session.getAttribute(SystemConstants.FALLTYPUS_LEVEL2_TYPE));
		
		cases.setParentType(parentFalltypusType);
		cases.setSubType(subTypeFalltypusType);
		cases.setUserId(String.valueOf(user.getId()));
		cases.setStatus(CaseStatus.NEW.getStatus());
		
		return caseRepository.save(cases);*/
		
		return null;
	}

	@Override
	public List<CasesDto> findAll() {
		List<Cases> cases = caseRepository.findAll();
		
		return CaseManager.caseWrapper(cases, caseQuestionAnswerRelRepository, falltypusRepository);
	}

	@Override
	public List<Cases> findByStatus(String status) {
		
		return caseRepository.findByStatus(UserManager.getSessionUser(httpSession), status);
	}
	
	@Override
	public List<CasesDto> findByType(String caseType) {
		List<Cases> cases = caseRepository.findByType(UserManager.getSessionUser(httpSession), caseType.equals(SystemConstants.CASE_TYPE_CONSULTING) ? "0" : "1");
		
		return CaseManager.caseWrapper(cases, caseQuestionAnswerRelRepository, falltypusRepository);
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
		return reservationRepository.queryAll(UserManager.getSessionUser(httpSession));
	}

	/**
	 * 更新案件状态
	 */
	@Override
	public void updateStatus(String statusCode, String caseId) {
		
		caseRepository.updateStatus(statusCode, caseId);
	}

	@Override
	public List<CasesDto> query(CasesCondition condition, Pageable pageable) {

		condition.setUserId(UserManager.getSessionUser(httpSession));
		Page<Cases> pageableCases = caseRepository.findAll(new CaseSpecification(condition), pageable);
		
		List<Cases> cases = pageableCases.getContent();
		
		return CaseManager.caseWrapper(cases, caseQuestionAnswerRelRepository, falltypusRepository);
		
		
	}
}
