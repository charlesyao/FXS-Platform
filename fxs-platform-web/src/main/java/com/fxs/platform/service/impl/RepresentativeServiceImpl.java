package com.fxs.platform.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fxs.platform.domain.Representative;
import com.fxs.platform.repository.RepresentativeRepository;
import com.fxs.platform.service.RepresentativeService;

@Service
@Transactional
public class RepresentativeServiceImpl implements RepresentativeService {

	@Autowired
	RepresentativeRepository representativeRepository;
	
	@Override
	@Cacheable(value="representatives")
	public List<Representative> findAll() {
		return representativeRepository.findAll();
	}

}
