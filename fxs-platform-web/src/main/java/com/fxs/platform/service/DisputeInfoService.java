package com.fxs.platform.service;

import java.util.List;

import com.fxs.platform.domain.DisputeInfo;

public interface DisputeInfoService {

	DisputeInfo getByDisputeInfoId(String disputeInfoId);

	List<DisputeInfo> getAllDisputeInfo();

	DisputeInfo save(DisputeInfo disputeInfo);

	void update(DisputeInfo disputeInfo);

	void view(DisputeInfo disputeInfo);

	void delete(String disputeInfoId);
}
