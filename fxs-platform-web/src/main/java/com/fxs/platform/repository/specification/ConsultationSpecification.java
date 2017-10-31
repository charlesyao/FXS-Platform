package com.fxs.platform.repository.specification;

import com.fxs.platform.domain.Consultation;
import com.fxs.platform.repository.condition.ConsultationCondition;
import com.fxs.platform.repository.support.FxsSpecification;
import com.fxs.platform.repository.support.QueryWraper;

/**
 * 
 * @author Charles
 *
 */
public class ConsultationSpecification extends FxsSpecification<Consultation, ConsultationCondition> {

	public ConsultationSpecification(ConsultationCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Consultation> queryWraper) {
		addEqualsCondition(queryWraper, "id");
		addEqualsCondition(queryWraper, "type");
		addEqualsCondition(queryWraper, "status");
	}
}
