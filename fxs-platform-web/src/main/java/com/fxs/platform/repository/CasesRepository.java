package com.fxs.platform.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Cases;

/**
 * @author Charles
 *
 */
@Repository
public interface CasesRepository extends FxsRepository<Cases> {
	List<Cases> findByTypeAndSubType(String caseType, String subType);
}
