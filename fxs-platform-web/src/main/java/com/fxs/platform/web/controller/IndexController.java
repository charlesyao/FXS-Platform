package com.fxs.platform.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author Charles
 *
 */
@Controller
public class IndexController {
	@GetMapping("/")
	public String index() {
		return "formsubmit";
	}
}
