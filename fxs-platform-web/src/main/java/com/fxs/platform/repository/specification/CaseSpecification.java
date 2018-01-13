package com.fxs.platform.repository.specification;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.repository.condition.CasesCondition;
import com.fxs.platform.repository.support.FxsSpecification;
import com.fxs.platform.repository.support.QueryWraper;

public class CaseSpecification extends FxsSpecification<Cases, CasesCondition> {

	public CaseSpecification(CasesCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Cases> queryWraper) {
		addInCondition(queryWraper, "parentType");
		addEqualsCondition(queryWraper, "id");
		addEqualsCondition(queryWraper, "caseType");
		addEqualsCondition(queryWraper, "userId");
		addEqualsCondition(queryWraper, "isRead");
		
		addLessThanCondition(queryWraper, "feedbackCount");
		addNotEqualsCondition(queryWraper, "status");
	}
}
