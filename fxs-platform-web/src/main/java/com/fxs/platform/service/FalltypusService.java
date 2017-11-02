/**
 * 
 */
package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.dto.FalltypusDto;

/**
 * @author Charles
 *
 */
public interface FalltypusService {
	List<FalltypusDto> findFirstLevelFalltypus();

	List<FalltypusDto> findSubFalltypusByParentId(String falltypusId);
}
