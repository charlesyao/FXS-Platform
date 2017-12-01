package com.fxs.platform.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.CaseFeedbackInfo;
import com.fxs.platform.domain.CaseQuestionAnswerRel;
import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.City;
import com.fxs.platform.domain.DetailedInquiry;
import com.fxs.platform.domain.Falltypus;
import com.fxs.platform.domain.Question;
import com.fxs.platform.dto.CaseFeedbackInfoDto;
import com.fxs.platform.dto.CaseQuestionAnswerRelDto;
import com.fxs.platform.dto.CasesDto;
import com.fxs.platform.repository.CaseFeedbackInfoRepository;
import com.fxs.platform.repository.CaseQuestionAnswerRelRepository;
import com.fxs.platform.repository.CasesRepository;
import com.fxs.platform.repository.CityRepository;
import com.fxs.platform.repository.DetailedInquiryRepository;
import com.fxs.platform.repository.FalltypusRepository;
import com.fxs.platform.repository.support.QueryResultConverter;

public class CaseManager {

	/**
	 * 保存案件对应的问题答案的关系
	 * @param cases
	 * @param session
	 * @param repository
	 */
	@SuppressWarnings("unchecked")
	public static void saveCaseQuestionRel(Cases cases, HttpSession session, CaseQuestionAnswerRelRepository repository) {
		CaseQuestionAnswerRel rel = null;
		Map<Integer, Object[]> qaMap = null;
		
		Object qaMapInSession = session.getAttribute(SystemConstants.QA_MAP);
		
		if (!ObjectUtils.isEmpty(qaMapInSession)) {
			qaMap = (Map<Integer, Object[]>) qaMapInSession;

			for (Object[] value : qaMap.values()) {
				

				Question question = (Question) value[0];
				List<Answer> answers = (List<Answer>) value[1];

				for (Answer answer : answers) {
					rel = new CaseQuestionAnswerRel();
					
					//如果选择了“不能回答”或者“确认后回答”，默认answerId为0
					rel.setAnswerId(String.valueOf(answer.getId()));
					
					//判断是否选择了正确的答案，还是选择了“不能回答”或者“确认后回答”
					rel.setAnswerDesc(ObjectUtils.isEmpty(answer.getDescription()) ? answer.getOther() : answer.getDescription());
					

					rel.setQuestionId(String.valueOf(question.getId()));
					rel.setQuestionDesc(question.getDescription());
					
					rel.setCaseId(cases.getId());
					
					repository.saveAndFlush(rel);
				}
			}
		}
	}

	/**
	 * 保存案件
	 * @param cases
	 * @param session
	 * @param repository
	 * @return
	 */
	public static Cases saveCase(Cases cases, HttpSession session, CasesRepository repository) {
		
		String level1TypeInSession = (String)session.getAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE);
		String level2TypeInSession = (String)session.getAttribute(SystemConstants.FALLTYPUS_LEVEL2_TYPE);
		String level1CityInSession = (String)session.getAttribute(SystemConstants.LEVEL_1_CITY);
		String level2CityInSession = (String)session.getAttribute(SystemConstants.LEVEL_2_CITY);
		
		if (!ObjectUtils.isEmpty(level1TypeInSession)) {
			cases.setParentType(level1TypeInSession);
		}
		
		if (!ObjectUtils.isEmpty(level2TypeInSession)) {
			cases.setSubType(level2TypeInSession);
		}
		
		if (!ObjectUtils.isEmpty(level1CityInSession)) {
			cases.setParentLocation(level1CityInSession);
		}
		
		if (!ObjectUtils.isEmpty(level2CityInSession)) {
			cases.setSubLocation(level2CityInSession);
		}
		
		cases.setUserId(UserManager.getSessionUser(session));
		
		cases.setStatus(CaseStatus.NEW.getStatus());
		
		cases.setIsRead(CaseStatus.UNREAD.getStatus());

		return repository.save(cases);
	}
	
	public static Page<CasesDto> caseWrapperPageable(Page<Cases> cases, 
			CaseQuestionAnswerRelRepository caseQuestionAnswerRelRepository,
			FalltypusRepository falltypusRepository, CityRepository cityRepository, Pageable pageable) {
		
		List<CasesDto> casesDtoList = new ArrayList<CasesDto>();
		
		
		for (Cases c : cases) {
			CasesDto caseDto = new CasesDto();
			BeanUtils.copyProperties(c, caseDto);
			
			falltypusWrapper(caseDto, c, falltypusRepository);
			cityWrapper(caseDto, c, cityRepository);
			List<CaseQuestionAnswerRel> rels = caseQuestionAnswerRelRepository.findAll(caseDto.getId());
			
			caseDto.setQaMapping(QueryResultConverter.convert(rels, CaseQuestionAnswerRelDto.class));
			
			casesDtoList.add(caseDto);
		}
		
		Page<CasesDto> page = new PageImpl<CasesDto>(casesDtoList, pageable, cases.getTotalElements());
		
		return page;
	}
	
	/**
	 * 封装案件列表
	 * @param cases
	 * @param caseQuestionAnswerRelRepository
	 * @param falltypusRepository
	 * @return
	 */
	public static List<CasesDto> caseWrapper(List<Cases> cases, 
			CaseQuestionAnswerRelRepository caseQuestionAnswerRelRepository,
			FalltypusRepository falltypusRepository, CityRepository cityRepository) {
		
		List<CasesDto> casesDtoList = new ArrayList<CasesDto>();
		
		
		for (Cases c : cases) {
			CasesDto caseDto = new CasesDto();
			BeanUtils.copyProperties(c, caseDto);
			
			falltypusWrapper(caseDto, c, falltypusRepository);
			cityWrapper(caseDto, c, cityRepository);
			List<CaseQuestionAnswerRel> rels = caseQuestionAnswerRelRepository.findAll(caseDto.getId());
			
			caseDto.setQaMapping(QueryResultConverter.convert(rels, CaseQuestionAnswerRelDto.class));
			
			casesDtoList.add(caseDto);
		}
		
		return casesDtoList;
	}
	
	/**
	 * 封装当个案件对象
	 * 
	 * @param cases
	 * @param caseQuestionAnswerRelRepository
	 * @param falltypusRepository
	 * @param detailedInquiryRepository
	 * @param caseFeedbackInfoRepository
	 * @param cityRepository
	 * @return
	 */
	public static CasesDto caseWrapperDetail(Cases cases, 
			CaseQuestionAnswerRelRepository caseQuestionAnswerRelRepository,
			FalltypusRepository falltypusRepository, DetailedInquiryRepository detailedInquiryRepository,
			CaseFeedbackInfoRepository caseFeedbackInfoRepository,
			CityRepository cityRepository,
			HttpSession session) {
		
		CasesDto caseDto = new CasesDto();
		BeanUtils.copyProperties(cases, caseDto);
		
		//转换案件类型
		falltypusWrapper(caseDto, cases, falltypusRepository);
		
		//转换城市
		cityWrapper(caseDto, cases, cityRepository);
		
		//获取所有当前案件相关的问题以及问题选择的选项
		List<CaseQuestionAnswerRel> rels = caseQuestionAnswerRelRepository.findAll(caseDto.getId());
		
		//获取当事人对当前案件添加的追问信息
		DetailedInquiry detailedInquiry = detailedInquiryRepository.findByCaseId(caseDto.getId());
		
		//获取律师对当前案件的反馈信息
		List<CaseFeedbackInfo> caseFeedbackInfo = caseFeedbackInfoRepository.findByCaseIdAndLawyerId(cases.getId(), UserManager.getSessionUser(session));
		
		caseDto.setQaMapping(QueryResultConverter.convert(rels, CaseQuestionAnswerRelDto.class));
		caseDto.setCaseFeedbackInfo(QueryResultConverter.convert(caseFeedbackInfo, CaseFeedbackInfoDto.class));
		
		if(! ObjectUtils.isEmpty(caseFeedbackInfo)) {
			//如果是打官司案件,每个律师只能又一次竞标,页面行为是竞标过了按钮为禁用
			if (cases.getCaseType().equals(CaseType.LAWSUIT.getType())) {
				caseDto.setDisableFeedback(caseFeedbackInfo.get(0).getLawyerId().equals(UserManager.getSessionUser(session)) 
						&& CaseStatus.BID.getStatus().equals(caseFeedbackInfo.get(0).getStatus()));
			//如果是咨询类案件,律师只能有一次反馈的机会，页面行为为反馈过了按钮禁用
			} else if (cases.getCaseType().equals(CaseType.CONSULTING.getType())) {
				caseDto.setDisableFeedback(caseFeedbackInfo.get(0).getLawyerId().equals(UserManager.getSessionUser(session)) 
						&& CaseStatus.FEEDBACK.getStatus().equals(caseFeedbackInfo.get(0).getStatus()));
			}
		} else {
			caseDto.setDisableFeedback(Boolean.FALSE);
		}
		
		List<String> detailedInquiries = new ArrayList<String>();
		
		if (! ObjectUtils.isEmpty(detailedInquiry)) {
			if (! ObjectUtils.isEmpty(detailedInquiry.getFirstComments())) {
				detailedInquiries.add(detailedInquiry.getFirstComments());
			}
			
			if (! ObjectUtils.isEmpty(detailedInquiry.getSecondComments())) {
				detailedInquiries.add(detailedInquiry.getSecondComments());
			}
			
			if (! ObjectUtils.isEmpty(detailedInquiry.getThirdComments())) {
				detailedInquiries.add(detailedInquiry.getThirdComments());
			}
			
		}
		
		caseDto.setDetailedInquiries(detailedInquiries);
		
		return caseDto;
	}
	
	/**
	 * 案件类型编号-名称转换
	 * @param caseDto
	 * @param cases
	 * @param falltypusRepository
	 */
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
	
	/**
	 * 城市编号-名称转换
	 * @param caseDto
	 * @param cases
	 * @param cityRepository
	 */
	private static void cityWrapper(CasesDto caseDto, Cases cases, CityRepository cityRepository) {
		if (! ObjectUtils.isEmpty(cases.getParentLocation())) {
			City level1CityName = cityRepository.findByCityId(cases.getParentLocation());
			
			if (! ObjectUtils.isEmpty(level1CityName)) {
				caseDto.setParentLocation(level1CityName.getName());
			}
		}
		
		if (! ObjectUtils.isEmpty(cases.getSubLocation())) {
			City level2CityName = cityRepository.findByCityId(cases.getSubLocation());
			
			if (! ObjectUtils.isEmpty(level2CityName)) {
				caseDto.setSubLocation(level2CityName.getName());
			}
		}
	}
}
