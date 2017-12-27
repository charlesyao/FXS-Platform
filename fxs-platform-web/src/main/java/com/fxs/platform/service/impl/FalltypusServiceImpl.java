package com.fxs.platform.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Falltypus;
import com.fxs.platform.dto.FalltypusDto;
import com.fxs.platform.repository.FalltypusRepository;
import com.fxs.platform.repository.support.QueryResultConverter;
import com.fxs.platform.service.FalltypusService;

@Service
@Transactional
public class FalltypusServiceImpl implements FalltypusService {

	@Autowired
	FalltypusRepository falltypusRepository;

	/* (non-Javadoc)
	 * @see com.fxs.platform.service.FalltypusService#findFirstLevelFalltypus()
	 */
	@Override
	@CachePut(value="_firstLevelFalltypus_")
	public List<FalltypusDto> findFirstLevelFalltypus() {
		// TODO Auto-generated method stub
		return QueryResultConverter.convert(falltypusRepository.findFirstLevelFalltypus(), FalltypusDto.class);
	}

	/* (non-Javadoc)
	 * @see com.fxs.platform.service.FalltypusService#findSubFalltypusByParentId(java.lang.String)
	 */
	@Override
	@CachePut(value="_subFalltypus_", key="#falltypusId")
	public List<FalltypusDto> findSubFalltypusByParentId(String falltypusId) {
		// TODO Auto-generated method stub
		return QueryResultConverter.convert(falltypusRepository.findSubFalltypusByParentId(falltypusId), FalltypusDto.class);
	}

	@Override
	public Falltypus create(Falltypus falltypus) {
		// TODO Auto-generated method stub
		falltypus.setId(String.valueOf(new Random().nextInt(99999999)));
		return falltypusRepository.save(falltypus);
	}

	@Override
	public List<FalltypusDto> findSubFalltypus() {
		// TODO Auto-generated method stub
		return QueryResultConverter.convert(falltypusRepository.findSubFalltypus(), FalltypusDto.class);
	}

	@Override
	public List<FalltypusDto> findAll() {
		// TODO Auto-generated method stub
		return QueryResultConverter.convert(falltypusRepository.findAll(), FalltypusDto.class);
	}

	@Override
	public void delete(String falltypusId) {
		//删除当前案件类型
		falltypusRepository.delete(falltypusId);
		
		//如果是一级类型，级联删除与之绑定的二级类型数据
		List<Falltypus> subFalltypus = falltypusRepository.findSubFalltypusByParentId(falltypusId);
		
		if (!ObjectUtils.isEmpty(subFalltypus)) {
			for (Falltypus falltypus : subFalltypus) {
				falltypusRepository.delete(falltypus.getId());
			}
		}
	}
}
