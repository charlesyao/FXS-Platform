package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.Lawsuit;
import com.fxs.platform.dto.LawsuitDto;

/**
 * 
 * @author Charles
 *
 */
public interface LawsuitService {
	Lawsuit create(Lawsuit lawsuit);

	List<LawsuitDto> query(String type);
}
