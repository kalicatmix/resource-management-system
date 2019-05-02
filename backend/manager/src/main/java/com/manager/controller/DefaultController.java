package com.manager.controller;

import org.springframework.aop.IntroductionInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="user")
public class DefaultController {

	@GetMapping(value="get")
	@ResponseBody
	public String getUser() {
		return "spring mvc";
	}
}
