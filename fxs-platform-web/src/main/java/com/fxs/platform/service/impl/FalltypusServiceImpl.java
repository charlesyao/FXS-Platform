/**
 * 
 */
package com.fxs.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fxs.platform.dto.FalltypusDto;
import com.fxs.platform.repository.FalltypusRepository;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.FalltypusService;

/**
 * @author Charles
 *
 */
@Service
public class FalltypusServiceImpl implements FalltypusService {

	@Autowired
	FalltypusRepository falltypusRepository;

	/* (non-Javadoc)
	 * @see com.fxs.platform.service.FalltypusService#findFirstLevelFalltypus()
	 */
	@Override
	@Cacheable(value="__parentFalltypus__")
	public List<FalltypusDto> findFirstLevelFalltypus() {
		// TODO Auto-generated method stub
		return QueryResultConverter.convert(falltypusRepository.findFirstLevelFalltypus(), FalltypusDto.class);
	}

	/* (non-Javadoc)
	 * @see com.fxs.platform.service.FalltypusService#findSubFalltypusByParentId(java.lang.String)
	 */
	@Override
	@Cacheable(value="_subFalltypus_", key="#falltypusId")
	public List<FalltypusDto> findSubFalltypusByParentId(String falltypusId) {
		// TODO Auto-generated method stub
		return QueryResultConverter.convert(falltypusRepository.findSubFalltypusByParentId(falltypusId), FalltypusDto.class);
	}
}
