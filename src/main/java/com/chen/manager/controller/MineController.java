package com.chen.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统——我的设置
 * 
 * created at 2019-11-06
 * 
 * @author MonoWing
 *
 */
@Controller
@RequestMapping("/admin/mine")
public class MineController {

	/**
	 * 页面跳转——基本资料页面
	 * @return
	 */
	@GetMapping("/baseInfo")
	public String baseInfo() {
		return "admin/mine/baseInfo";
	}

	/**
	 * 页面跳转——修改密码页面
	 * @return
	 */
	@GetMapping("/changePsd")
	public String changePsd() {
		return "/admin/mine/changePsd";
	}

}
