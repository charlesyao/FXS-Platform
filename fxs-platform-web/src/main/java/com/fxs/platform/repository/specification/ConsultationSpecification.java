package com.fxs.platform.repository.specification;

import com.fxs.platform.domain.Reservation;
import com.fxs.platform.repository.condition.ConsultationCondition;
import com.fxs.platform.repository.support.FxsSpecification;
import com.fxs.platform.repository.support.QueryWraper;

public class ConsultationSpecification extends FxsSpecification<Reservation, ConsultationCondition> {

	public ConsultationSpecification(ConsultationCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Reservation> queryWraper) {
		addEqualsCondition(queryWraper, "id");
		addEqualsCondition(queryWraper, "type");
		addEqualsCondition(queryWraper, "status");
		addInCondition(queryWraper, "falltypus");
	}
}
