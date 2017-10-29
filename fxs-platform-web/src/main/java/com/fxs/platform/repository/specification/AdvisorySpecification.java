package com.fxs.platform.repository.specification;

import com.fxs.platform.domain.Advisory;
import com.fxs.platform.repository.condition.AdvisoryCondition;
import com.fxs.platform.repository.support.FxsSpecification;
import com.fxs.platform.repository.support.QueryWraper;

/**
 * 
 * @author Charles
 *
 */
public class AdvisorySpecification extends FxsSpecification<Advisory, AdvisoryCondition> {

	public AdvisorySpecification(AdvisoryCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Advisory> queryWraper) {
		addEqualsCondition(queryWraper, "type");
	}
}
