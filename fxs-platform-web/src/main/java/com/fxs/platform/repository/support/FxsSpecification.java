
package com.fxs.platform.repository.support;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * 
 * @param <T>
 * @param <C>
 */
public abstract class FxsSpecification<T, C> extends AbstractEventConditionBuilder<T, C> implements Specification<T> {

	public FxsSpecification(C condition) {
		super(condition);
	}

	/**
	 * 
	 * 构建查询条件，子类必须实现addCondition方法来编写查询的逻辑。
	 * 
	 * 
	 */
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		List<Predicate> predicates = new ArrayList<Predicate>();

		QueryWraper<T> queryWraper = new QueryWraper<T>(root, query, cb, predicates);

		addCondition(queryWraper);

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	protected abstract void addCondition(QueryWraper<T> queryWraper);

}
