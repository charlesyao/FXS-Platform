package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

	@Override
	public List<Lawsuit> findByType(String lawsuitType) {
		return lawsuitRepository.findByType(lawsuitType);
	}

	@Override
	public List<Lawsuit> findByStatus(String lawsuitStatus) {
		return lawsuitRepository.findByStatus(lawsuitStatus);
	}

	@Override
	public Lawsuit findByLawsuitId(String lawsuitId) {

		return lawsuitRepository.findOne(lawsuitId);
	}

	@Override
	public List<Lawsuit> findAll() {
		return lawsuitRepository.findAll();
	}

	@Override
	public Lawsuit update(String lawsuitId, Lawsuit lawsuit) {
		Lawsuit l = lawsuitRepository.findOne(lawsuitId);
		if (!ObjectUtils.isEmpty(l)) {
			BeanUtils.copyProperties(lawsuit, l);
		}

		return create(l);
	}
}
