package com.fxs.platform.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxs.platform.domain.Falltypus;
import com.fxs.platform.dto.FalltypusDto;
import com.fxs.platform.security.core.i18n.LocaleMessageSourceService;
import com.fxs.platform.security.core.support.ResponseMessage;
import com.fxs.platform.security.core.support.Result;
import com.fxs.platform.service.FalltypusService;

@Controller
@RequestMapping("/falltypus")
public class FalltypusController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	FalltypusService falltypusService;
	
	@PostMapping
	@ResponseBody
	public ResponseMessage<Falltypus> create(@Valid @RequestBody Falltypus falltypus) {
		return Result.success(falltypusService.create(falltypus));
	}
	
	/**
	 *
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public ResponseMessage<List<FalltypusDto>> getFirstLevelFalltypus() {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.falltypus"),
				falltypusService.findFirstLevelFalltypus());
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseMessage<List<FalltypusDto>> getSubFalltypus(@PathVariable String id) {
		return Result.success(localeMessageSourceService.getMessage("fxs.platform.application.falltypus"),
				falltypusService.findSubFalltypusByParentId(id));
	}
}
