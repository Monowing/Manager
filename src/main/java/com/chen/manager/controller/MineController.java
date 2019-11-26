package com.chen.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统——我的
 * 
 * created at 2019-11-06
 * 
 * @author MonoWing
 *
 */
@Controller
@RequestMapping("/admin/mine")
public class MineController {

	@GetMapping("/baseInfo")
	public String baseInfo() {
		return "admin/mine/baseInfo";
	}

	@GetMapping("/changePsd")
	public String changePsd() {
		return "/admin/mine/changePsd";
	}

}
