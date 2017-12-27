package com.fxs.platform.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.DisputeInfo;
import com.fxs.platform.repository.DisputeInfoRepository;
import com.fxs.platform.service.DisputeInfoService;

@Service
@Transactional
public class DisputeInfoServiceImpl implements DisputeInfoService {
	@Autowired
	private DisputeInfoRepository disputeInfoRepository;

	public DisputeInfo getByDisputeInfoId(String dId) {
		return disputeInfoRepository.findOne(dId);
	}

	public List<DisputeInfo> getAllDisputeInfo() {
		return disputeInfoRepository.findAll();
	}

	public DisputeInfo save(DisputeInfo disputeInfo) {
		disputeInfo.setId(String.valueOf(new Random().nextInt(99999999)));
		return disputeInfoRepository.save(disputeInfo);
	}

	public void update(DisputeInfo disputeInfo) {
		save(disputeInfo);
	}

	public void view(DisputeInfo disputeInfo) {
		save(disputeInfo);
	}

	public void delete(String dId) {
		DisputeInfo dispute = getByDisputeInfoId(dId);
		
		if(!ObjectUtils.isEmpty(dispute)) {
			disputeInfoRepository.delete(dispute);
		}
	}

}
