package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Falltypus;
import com.fxs.platform.dto.FalltypusDto;

public interface FalltypusService {
	Falltypus create(Falltypus falltypus);
	
	List<FalltypusDto> findFirstLevelFalltypus();
	
	List<FalltypusDto> findSubFalltypus();
	
	List<FalltypusDto> findAll();

	List<FalltypusDto> findSubFalltypusByParentId(String falltypusId);
}
