package com.fxs.platform.service.impl;

import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.DetailedInquiry;
import com.fxs.platform.repository.DetailedInquiryRepository;
import com.fxs.platform.service.DetailedInquiryService;

@Service
@Transactional
public class DetailedInquiryServiceImpl implements DetailedInquiryService {

	@Autowired
    HttpSession session;
	
	@Autowired
	DetailedInquiryRepository detailedInquiryRepository;
	
	@Override
	public DetailedInquiry save(String caseId, Cases cases) {
		DetailedInquiry di = null;
		
		DetailedInquiry detailedInquiry = detailedInquiryRepository.findByCaseId(caseId);
		
		//律师针对当事人的提问回复信息
		if(!ObjectUtils.isEmpty(cases.getDetailedInquirysIndex()) && !ObjectUtils.isEmpty(detailedInquiry)) {
			switch (cases.getDetailedInquirysIndex()) {
			case "1":
				detailedInquiry.setFirstFeedback(cases.getDetailedInquirys());
				return detailedInquiryRepository.save(detailedInquiry);
			case "2":
				detailedInquiry.setSecondFeedback(cases.getDetailedInquirys());
				return detailedInquiryRepository.save(detailedInquiry);
			case "3":
				detailedInquiry.setThirdFeedback(cases.getDetailedInquirys());
				return detailedInquiryRepository.save(detailedInquiry);
			}
		} else {
			if (ObjectUtils.isEmpty(detailedInquiry)) {
				//第一次追问
				di = new DetailedInquiry();
				di.setId(String.valueOf(new Random().nextInt(99999999)));
				di.setFirstComments(cases.getDetailedInquirys());
				di.setCaseId(caseId);
				
				return detailedInquiryRepository.save(di);
			} else {
				if (ObjectUtils.isEmpty(detailedInquiry.getSecondComments())) {
					//第二次追问
					detailedInquiry.setSecondComments(cases.getDetailedInquirys());
					return detailedInquiryRepository.save(detailedInquiry);
				} else if (ObjectUtils.isEmpty(detailedInquiry.getThirdComments())) {
					//第三次追问
					detailedInquiry.setThirdComments(cases.getDetailedInquirys());
					return detailedInquiryRepository.save(detailedInquiry);
				}
			}
		}
		
		//追问次数达到最大
		return null;
		
	}
}
