package com.fxs.platform.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.CaseQuestionAnswerRel;
import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.Falltypus;
import com.fxs.platform.domain.Question;
import com.fxs.platform.dto.CaseQuestionAnswerRelDto;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CaseQuestionAnswerRelRepository;
import com.fxs.platform.repository.CasesRepository;
import com.fxs.platform.repository.FalltypusRepository;
import com.fxs.platform.repository.support.QueryResultConverter;

public class CaseManager {

	@SuppressWarnings("unchecked")
	public static void saveCaseQuestionRel(Cases cases, HttpSession session, CaseQuestionAnswerRelRepository repository) {
		CaseQuestionAnswerRel rel = null;
		Map<Integer, Object[]> qaMap = null;
		
		Object qaMapInSession = session.getAttribute(SystemConstants.QA_MAP);
		
		if (!ObjectUtils.isEmpty(qaMapInSession)) {
			qaMap = (Map<Integer, Object[]>) qaMapInSession;

			for (Object[] value : qaMap.values()) {
				rel = new CaseQuestionAnswerRel();

				Question question = (Question) value[0];
				Answer answer = (Answer) value[1];

				rel.setAnswerId(String.valueOf(answer.getId()));
				rel.setAnswerDesc(answer.getDescription());

				rel.setQuestionId(String.valueOf(question.getId()));
				rel.setQuestionDesc(question.getDescription());
				
				rel.setCaseId(cases.getId());
				
				repository.save(rel);
			}
		}

	}

	public static Cases saveCase(Cases cases, HttpSession session, CasesRepository repository) {
		
		String level1TypeInSession = (String)session.getAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE);
		String level2TypeInSession = (String)session.getAttribute(SystemConstants.FALLTYPUS_LEVEL2_TYPE);
		
		if (!ObjectUtils.isEmpty(level1TypeInSession)) {
			cases.setParentType(level1TypeInSession);
		}
		
		if (!ObjectUtils.isEmpty(level2TypeInSession)) {
			cases.setSubType(level2TypeInSession);
		}
		
		cases.setUserId(UserManager.getSessionUser(session));

		return repository.save(cases);
	}
	
	public static List<CasesDto> caseWrapper(List<Cases> cases, 
			CaseQuestionAnswerRelRepository caseQuestionAnswerRelRepository,
			FalltypusRepository falltypusRepository) {
		
		List<CasesDto> casesDtoList = new ArrayList<CasesDto>();
		
		
		for (Cases c : cases) {
			CasesDto caseDto = new CasesDto();
			BeanUtils.copyProperties(c, caseDto);
			
			falltypusWrapper(caseDto, c, falltypusRepository);
			
			List<CaseQuestionAnswerRel> rels = caseQuestionAnswerRelRepository.findAll(caseDto.getId());
			
			caseDto.setQaMapping(QueryResultConverter.convert(rels, CaseQuestionAnswerRelDto.class));
			
			casesDtoList.add(caseDto);
		}
		
		return casesDtoList;
	}
	
	public static CasesDto caseWrapper(Cases cases, 
			CaseQuestionAnswerRelRepository caseQuestionAnswerRelRepository,
			FalltypusRepository falltypusRepository) {
		
		CasesDto caseDto = new CasesDto();
		BeanUtils.copyProperties(cases, caseDto);
		
		falltypusWrapper(caseDto, cases, falltypusRepository);
		
		List<CaseQuestionAnswerRel> rels = caseQuestionAnswerRelRepository.findAll(caseDto.getId());
		
		caseDto.setQaMapping(QueryResultConverter.convert(rels, CaseQuestionAnswerRelDto.class));
		
		return caseDto;
	}
	
	private static void falltypusWrapper(CasesDto caseDto, Cases cases, FalltypusRepository falltypusRepository) {
		if (! ObjectUtils.isEmpty(cases.getParentType())) {
			Falltypus type = falltypusRepository.findById(cases.getParentType());
			
			if (! ObjectUtils.isEmpty(type)) {
				caseDto.setParentType(type.getName());
			}
		}
		
		if (! ObjectUtils.isEmpty(cases.getSubType())) {
			Falltypus type = falltypusRepository.findById(cases.getSubType());
			
			if (! ObjectUtils.isEmpty(type)) {
				caseDto.setSubType(type.getName());
			}
		}
	}
}
