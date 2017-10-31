package com.fxs.platform.repository.specification;

import com.fxs.platform.domain.Consultation;
import com.fxs.platform.repository.condition.AdvisoryCondition;
import com.fxs.platform.repository.support.FxsSpecification;
import com.fxs.platform.repository.support.QueryWraper;

/**
 * 
 * @author Charles
 *
 */
public class AdvisorySpecification extends FxsSpecification<Consultation, AdvisoryCondition> {

	public AdvisorySpecification(AdvisoryCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Consultation> queryWraper) {
		addEqualsCondition(queryWraper, "type");
	}
}
