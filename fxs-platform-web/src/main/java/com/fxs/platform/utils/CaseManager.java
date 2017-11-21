package com.fxs.platform.utils;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.domain.CaseQuestionAnswerRel;
import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.Question;
import com.fxs.platform.domain.User;
import com.fxs.platform.repository.CaseQuestionAnswerRelRepository;
import com.fxs.platform.repository.CasesRepository;

public class CaseManager {

	@SuppressWarnings("unchecked")
	public static void saveCaseQuestionRel(HttpSession session, CaseQuestionAnswerRelRepository repository) {
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

				repository.save(rel);
			}
		}

	}

	public static void saveCase(Cases cases, HttpSession session, CasesRepository repository) {
		
		if (!ObjectUtils.isEmpty(session.getAttribute("userInfo"))) {
			User user = (User)(session.getAttribute("userInfo"));
			cases.setUserId(String.valueOf(user.getId()));
		}

		if (!ObjectUtils.isEmpty(session.getAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE))) {
			String parentFalltypusId = String.valueOf((session.getAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE)));
			cases.setParentType(parentFalltypusId);
		}
		
		if (!ObjectUtils.isEmpty(session.getAttribute(SystemConstants.FALLTYPUS_LEVEL2_TYPE))) {
			String subFalltypusId = String.valueOf((session.getAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE)));
			cases.setSubType(subFalltypusId);
		}
		
		repository.save(cases);
	}
}
