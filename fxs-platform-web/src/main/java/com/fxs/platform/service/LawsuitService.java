package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Lawsuit;

/**
 * 
 * @author Charles
 *
 */
public interface LawsuitService {
	Lawsuit create(Lawsuit lawsuit);

	List<Lawsuit> findByType(String lawsuitType);
	
	List<Lawsuit> findByStatus(String lawsuitStatus);

	Lawsuit findByLawsuitId(String lawsuitId);

	List<Lawsuit> findAll();

	Lawsuit update(String lawsuitId, Lawsuit lawsuit);
}
