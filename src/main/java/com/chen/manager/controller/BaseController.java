package com.chen.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

	
	@RequestMapping("/index")
	private String index(){
		return "/admin/base/index";
	}
	
	@RequestMapping("/main")
	private String main(){
		return "/admin/base/main";
	}
	
}
