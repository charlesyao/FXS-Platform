package com.fxs.platform.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxs.platform.domain.Falltypus;
import com.fxs.platform.repository.FalltypusRepository;

/**
 * Default data initializer
 * 
 * @author Charles
 *
 */
@Component
public class FalltypusDataInitializer extends AbstractDataInitializer {

	@Autowired
	private FalltypusRepository falltypusRepository;

	@Override
	public Integer getIndex() {
		return Integer.MIN_VALUE;
	}

	@Override
	protected void doInit() {
		initFalltypus();
	}

	private void initFalltypus() {
		List<String> falltypusList = Arrays.asList(
				"劳动纠纷", "婚姻家庭、继承、同居", "借贷纠纷、投资理财", 
				"交通事故、医疗纠纷", "房屋买卖、房屋租赁、动拆迁",
				"商品、货物买卖纠纷", "知识产权、加盟纠纷", "刑事案件", "其他纠纷");

		Falltypus falltypus = null;
		List<Falltypus> list = new ArrayList<Falltypus>();

		for (String falltypusDesc : falltypusList) {
			falltypus = new Falltypus();
			falltypus.setDesc(falltypusDesc);
			list.add(falltypus);
		}

		falltypusRepository.save(list);
	}

	@Override
	protected boolean isNeedInit() {
		return falltypusRepository.count() == 0;
	}
}
