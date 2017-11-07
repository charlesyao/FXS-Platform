package com.fxs.platform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fxs.platform.domain.Lawsuit;
import com.fxs.platform.dto.LawsuitDto;
import com.fxs.platform.repository.condition.LawsuitCondition;

public interface LawsuitService {
	Lawsuit create(Lawsuit lawsuit);

	Page<LawsuitDto> query(LawsuitCondition condition, Pageable pageable);

	Lawsuit update(String lawsuitId, Lawsuit lawsuit);

}
