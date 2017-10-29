package com.fxs.platform.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fxs.platform.domain.Lawsuit;
import com.fxs.platform.dto.LawsuitDto;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseCodeType;
import com.fxs.platform.security.core.support.SimpleResponse;
import com.fxs.platform.service.LawsuitService;

/**
 * 打官司接口
 * 
 * @author Charles
 *
 */
@RestController
@RequestMapping("/lawsuit")
public class LawsuitController {
	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	LawsuitService lawsuitService;

	/**
	 * 提交打官司信息
	 * 
	 * @param lawsuit
	 * @return
	 */
	@PostMapping
	public SimpleResponse<Lawsuit> advisory(@Valid @RequestBody Lawsuit lawsuit) {
		return new SimpleResponse<Lawsuit>(ResponseCodeType.ZERO.getValue(),
				localeMessageSourceService.getMessage("fxs.platform.application.lawsuit.save"),
				lawsuitService.create(lawsuit));
	}

	/**
	 * 根据类型获取打官司信息
	 * 类型: 自助/委托律师
	 * 
	 * @param type
	 * @return
	 */
	@GetMapping("/{type}")
	public SimpleResponse<List<LawsuitDto>> query(@PathVariable String type) {

		return new SimpleResponse<List<LawsuitDto>>(ResponseCodeType.ZERO.getValue(),
				localeMessageSourceService.getMessage("fxs.platform.application.lawsuit.get", new Object[] { type }),
				lawsuitService.query(type));
	}
}
