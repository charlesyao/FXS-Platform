package com.fxs.platform.repository.specification;

import com.fxs.platform.domain.Reservation;
import com.fxs.platform.repository.condition.ReservationCondition;
import com.fxs.platform.repository.support.FxsSpecification;
import com.fxs.platform.repository.support.QueryWraper;

public class ReservationSpecification extends FxsSpecification<Reservation, ReservationCondition> {

	public ReservationSpecification(ReservationCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Reservation> queryWraper) {
		addEqualsCondition(queryWraper, "isRead");
		addInCondition(queryWraper, "status");
	}
}
