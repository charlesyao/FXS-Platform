package com.fxs.platform.repository.support;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 包装用于构建JPA动态查询时所需的对象
 * 
 */
public class QueryWraper<T> {

	/**
	 * @param root
	 *            JPA Root
	 * @param cb
	 *            JPA CriteriaBuilder
	 * @param predicates
	 *            JPA Predicate
	 */
	public QueryWraper(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<Predicate> predicates) {
		this.root = root;
		this.query = query;
		this.cb = cb;
		this.predicates = predicates;
	}

	private Root<T> root;

	private CriteriaBuilder cb;

	private List<Predicate> predicates;

	private CriteriaQuery<?> query;

	public void addPredicate(Predicate predicate) {
		this.predicates.add(predicate);
	}

	public Root<T> getRoot() {
		return root;
	}

	public void setRoot(Root<T> root) {
		this.root = root;
	}

	public CriteriaBuilder getCb() {
		return cb;
	}

	public void setCb(CriteriaBuilder cb) {
		this.cb = cb;
	}

	public List<Predicate> getPredicates() {
		return predicates;
	}

	public void setPredicates(List<Predicate> predicates) {
		this.predicates = predicates;
	}

	public CriteriaQuery<?> getQuery() {
		return query;
	}

	public void setQuery(CriteriaQuery<?> query) {
		this.query = query;
	}

}
