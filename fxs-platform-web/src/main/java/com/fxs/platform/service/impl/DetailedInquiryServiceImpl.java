package com.fxs.platform.service.impl;

import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
	public DetailedInquiry save(String caseId, String content) {
		DetailedInquiry di = null;
		
		DetailedInquiry detailedInquiry = detailedInquiryRepository.findByCaseId(caseId);
		
		if (ObjectUtils.isEmpty(detailedInquiry)) {
			//第一次追问
			di = new DetailedInquiry();
			di.setId(String.valueOf(new Random().nextInt(99999999)));
			di.setFirstComments(content);
			di.setCaseId(caseId);
			
			return detailedInquiryRepository.save(di);
		} else {
			if (ObjectUtils.isEmpty(detailedInquiry.getSecondComments())) {
				//第二次追问
				detailedInquiry.setSecondComments(content);
				return detailedInquiryRepository.save(detailedInquiry);
			} else if (ObjectUtils.isEmpty(detailedInquiry.getThirdComments())) {
				//第三次追问
				detailedInquiry.setThirdComments(content);
				return detailedInquiryRepository.save(detailedInquiry);
			}
		}
		
		//追问次数达到最大
		return null;
		
	}
}
