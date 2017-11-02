package com.fxs.platform.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import com.fxs.platform.domain.Falltypus;
import com.fxs.platform.repository.FalltypusRepository;

/**
 * Default data initializer
 * 
 * @author Charles
 *
 */
@Component
@Order(1)
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

		Map<String, List<String>> map = new TreeMap<String, List<String>>();

		map.put("劳动纠纷", Arrays.asList());
		map.put("婚姻家庭、继承、同居", Arrays.asList("婚姻纠纷（离婚、财产分割、子女抚养）", "同居纠纷（未办理结婚登记）", "继承纠纷", "其他家庭纠纷"));
		map.put("借贷纠纷、投资理财", Arrays.asList("借贷纠纷", "投资理财纠纷"));
		map.put("交通事故、医疗纠纷", Arrays.asList("交通事故", "医疗纠纷（特指医院存在治疗过失）"));
		map.put("房屋买卖、房屋租赁、动拆迁", Arrays.asList("房屋买卖", "房屋租赁", "房屋拆迁"));
		map.put("商品、货物买卖纠纷", Arrays.asList());
		map.put("知识产权、加盟纠纷", Arrays.asList("商标纠纷", "专利纠纷", "著作权纠纷", "加盟纠纷", "其他知识产权纠纷"));
		map.put("刑事案件", Arrays.asList());
		map.put("其他纠纷", Arrays.asList("股东纠纷、合伙纠纷", "房屋装修、物业纠纷、邻里纠纷", "姓名、肖像、名誉、隐私纠纷", "人身伤害", "行政纠纷（与政府部门的纠纷）",
				"建设工程、土地纠纷", "服务合同纠纷、消费维权", "合同纠纷", "物流、运输纠纷", "其他纠纷"));

		Falltypus falltypus = null;
		int parentStep = 0;

		for (String key : map.keySet()) {
			int subStep = 0;

			parentStep += 1;

			falltypus = new Falltypus();

			List<String> subFalltypusList = map.get(key);

			falltypus.setName(key);

			String typeId = StringUtils.toString(Integer.parseInt("1000000") + parentStep);
			falltypus.setTypeId(typeId);

			Falltypus f = falltypusRepository.save(falltypus);

			if (!subFalltypusList.isEmpty()) {
				List<Falltypus> list = new ArrayList<Falltypus>();

				for (String subFalltypus : subFalltypusList) {
					subStep += 1;
					falltypus = new Falltypus();

					falltypus.setName(subFalltypus);

					falltypus.setTypeId(StringUtils.toString(Integer.parseInt(typeId + "100") + subStep));
					falltypus.setParentTypeId(f.getTypeId());

					list.add(falltypus);
				}

				falltypusRepository.save(list);
			}

		}
	}

	@Override
	protected boolean isNeedInit() {
		return falltypusRepository.count() == 0;
	}
}
