package com.fxs.platform.repository;

import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.DetailedInquiry;

@Repository
public interface DetailedInquiryRepository extends FxsRepository<DetailedInquiry> {

	DetailedInquiry findByCaseId(String caseId);
}
