/**
 * 
 */
package com.fxs.platform.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Charles
 *
 */
@Controller
@RequestMapping("/user")
public class LoginController {
	@GetMapping("/signIn")
	public String login() {
		return "signIn";
	}
}
