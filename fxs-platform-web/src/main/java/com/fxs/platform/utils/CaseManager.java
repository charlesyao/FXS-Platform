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

		if (!ObjectUtils.isEmpty(session.getAttribute(SystemConstants.QA_MAP))) {
			qaMap = (Map<Integer, Object[]>) session.getAttribute(SystemConstants.QA_MAP);

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
		
		if (!ObjectUtils.isEmpty(session.getAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE))) {
			String parentFalltypusId = String.valueOf((session.getAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE)));
			cases.setParentType(parentFalltypusId);
		}
		
		if (!ObjectUtils.isEmpty(session.getAttribute(SystemConstants.FALLTYPUS_LEVEL2_TYPE))) {
			String subFalltypusId = String.valueOf((session.getAttribute(SystemConstants.FALLTYPUS_LEVEL2_TYPE)));
			cases.setSubType(subFalltypusId);
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
			
			if (! ObjectUtils.isEmpty(c.getParentType())) {
				Falltypus type = falltypusRepository.findById(c.getParentType());
				
				if (! ObjectUtils.isEmpty(type)) {
					caseDto.setParentType(type.getName());
				}
			}
			
			if (! ObjectUtils.isEmpty(c.getSubType())) {
				Falltypus type = falltypusRepository.findById(c.getSubType());
				
				if (! ObjectUtils.isEmpty(type)) {
					caseDto.setSubType(type.getName());
				}
			}
			
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
		
		caseDto.setParentType(falltypusRepository.findById(cases.getParentType()).getName());
		caseDto.setSubType(falltypusRepository.findById(cases.getSubType()).getName());
		
		List<CaseQuestionAnswerRel> rels = caseQuestionAnswerRelRepository.findAll(caseDto.getId());
		
		caseDto.setQaMapping(QueryResultConverter.convert(rels, CaseQuestionAnswerRelDto.class));
		
		return caseDto;
	}
}
