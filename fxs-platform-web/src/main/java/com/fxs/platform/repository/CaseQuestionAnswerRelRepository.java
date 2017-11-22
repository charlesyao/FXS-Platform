package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.fxs.platform.domain.CaseQuestionAnswerRel;

public interface CaseQuestionAnswerRelRepository  extends FxsRepository<CaseQuestionAnswerRel> {
	@Query("SELECT o FROM CaseQuestionAnswerRel o where o.caseId=?1")
	List<CaseQuestionAnswerRel> findAll(String caseId);
}
