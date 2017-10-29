package com.fxs.platform.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Advisory;

/**
 * @author Charles
 *
 */
@Repository
public interface AdvisoryRepository extends FxsRepository<Advisory> {
	List<Advisory> findByType(String advisoryType);
}
