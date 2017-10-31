package com.fxs.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.fxs.platform.repository.FalltypusRepository;

/**
 * 
 * @author Charles
 *
 */
@Controller
public class IndexController {
	@Autowired
	FalltypusRepository falltypusRepository;
	
	@GetMapping("/")
	public String index(ModelMap map) {
		map.put("falltypusdata", falltypusRepository.findAll());
		return "formsubmit";
	}
}
