package com.fxs.platform.repository.specification;

import com.fxs.platform.domain.Answer;
import com.fxs.platform.repository.condition.AnswerCondition;
import com.fxs.platform.repository.support.FxsSpecification;
import com.fxs.platform.repository.support.QueryWraper;

public class AnswerSpecification extends FxsSpecification<Answer, AnswerCondition> {

	public AnswerSpecification(AnswerCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Answer> queryWraper) {
		addEqualsCondition(queryWraper, "id");
	}
}
