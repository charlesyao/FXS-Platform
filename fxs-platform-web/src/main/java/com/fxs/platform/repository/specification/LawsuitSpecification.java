package com.fxs.platform.repository.specification;

import com.fxs.platform.domain.Lawsuit;
import com.fxs.platform.repository.condition.LawsuitCondition;
import com.fxs.platform.repository.support.FxsSpecification;
import com.fxs.platform.repository.support.QueryWraper;

/**
 * 
 * @author Charles
 *
 */
public class LawsuitSpecification extends FxsSpecification<Lawsuit, LawsuitCondition> {

	public LawsuitSpecification(LawsuitCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Lawsuit> queryWraper) {
		addEqualsCondition(queryWraper, "id");
		addEqualsCondition(queryWraper, "type");
		addEqualsCondition(queryWraper, "status");
	}
}
