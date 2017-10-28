package com.fxs.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.Lawsuit;
import com.fxs.platform.repository.LawsuitRepository;
import com.fxs.platform.service.LawsuitService;

/**
 * 
 * @author Charles
 *
 */
@Service
public class LawsuitServiceImpl implements LawsuitService {

	@Autowired
	LawsuitRepository lawsuitRepository;

	@Override
	public Lawsuit create(Lawsuit lawsuit) {
		return lawsuitRepository.save(lawsuit);
	}
}
