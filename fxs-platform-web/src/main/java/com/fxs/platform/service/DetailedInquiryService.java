package com.fxs.platform.service;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.DetailedInquiry;

public interface DetailedInquiryService {
	
	DetailedInquiry save(String caseId, Cases cases);
}
