package com.fxs.platform.service;

import com.fxs.platform.domain.DetailedInquiry;

public interface DetailedInquiryService {
	
	DetailedInquiry save(String caseId, String content);
}
