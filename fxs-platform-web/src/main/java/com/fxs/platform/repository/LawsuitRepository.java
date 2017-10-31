package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Lawsuit;

/**
 * @author Charles
 *
 */
@Repository
public interface LawsuitRepository extends FxsRepository<Lawsuit> {

	List<Lawsuit> findByType(String lwsuitType);

	List<Lawsuit> findByStatus(String status);

	@Query("SELECT o FROM Lawsuit o where o.id=?1")
	Lawsuit findOne(String lwsuitId);
}
