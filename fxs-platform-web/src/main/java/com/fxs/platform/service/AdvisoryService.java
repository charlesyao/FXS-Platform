package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Advisory;
import com.fxs.platform.dto.AdvisoryDto;

/**
 * 
 * @author Charles
 *
 */
public interface AdvisoryService {
	Advisory create(Advisory advisory);

	List<AdvisoryDto> query(String type);
}
