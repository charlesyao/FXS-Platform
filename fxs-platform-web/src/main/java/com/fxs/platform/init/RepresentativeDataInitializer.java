package com.fxs.platform.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxs.platform.domain.Representative;
import com.fxs.platform.repository.RepresentativeRepository;

@Component
public class RepresentativeDataInitializer extends AbstractDataInitializer {

	@Autowired
	private RepresentativeRepository representativeRepository;

	@Override
	public Integer getIndex() {
		return Integer.MIN_VALUE;
	}

	@Override
	protected void doInit() {
		initRepresentative();
	}

	private void initRepresentative() {
		List<Representative> rl = new ArrayList<Representative>();

		List<String> rList = Arrays.asList("本人", "家人", "朋友", "企业");

		for (String r : rList) {
			Representative nr = new Representative();
			nr.setType(r);
			rl.add(nr);
		}

		representativeRepository.save(rl);
	}

	@Override
	protected boolean isNeedInit() {
		return representativeRepository.count() == 0;
	}
}
