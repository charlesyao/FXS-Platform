package com.fxs.platform.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.fxs.platform.service.RepresentativeService;

@Controller
@RequestMapping("/falltypus")
public class FalltypusController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	FalltypusService falltypusService;
	
	@Autowired
	RepresentativeService representativeService;
	
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
	@GetMapping("/public/consultation/{id}")
	public String getSubFalltypusForConsultation(@PathVariable String id, ModelMap map) {
		String target = "";
		List<FalltypusDto> subFalltypusList = falltypusService.findSubFalltypusByParentId(id);
		
		if (subFalltypusList.size() == 0) {
			map.addAttribute("representativeList", representativeService.findAll());
			
			target = "public_consulting_free_step2";
		} else {
			map.addAttribute("subFalltypusList", subFalltypusList);
			
			target = "public_consulting_free_step1";
		}
		
		return target;
	}
	
	@GetMapping("/public/lawsuit/{id}")
	public String getSubFalltypusForLawsuit(@PathVariable String id, ModelMap map) {
		String target = "";
		List<FalltypusDto> subFalltypusList = falltypusService.findSubFalltypusByParentId(id);
		
		if (subFalltypusList.size() == 0) {
			map.addAttribute("representativeList", representativeService.findAll());
			
			target = "public_lawsuit_lawyer_step3";
		} else {
			map.addAttribute("subFalltypusList", subFalltypusList);
			
			target = "public_lawsuit_lawyer_step2";
		}
		
		return target;
	}
}
