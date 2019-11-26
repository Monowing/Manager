package com.chen.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.manager.service.AdminService;
import com.chen.manager.viewmodel.CommonResult;

/**
 * 系统——管理员
 * 
 * created at 2019-11-05
 * 
 * @author MonoWing
 *
 */
@Controller
@RequestMapping("/admin/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	/**
	 * 管理员页
	 * @return
	 */
	@GetMapping("/index")
	public String index(){
		return "admin/admin/index";
	}
	
	/**
	 * 获取管理员基本信息
	 * @param token Token值
	 * @return
	 */
	@GetMapping("/getAdminBaseInfoView")
	@ResponseBody
	public CommonResult getAdminBaseInfoView(String token){
		return new CommonResult().success(adminService.getAdminBaseInfoView(token));
	}
}
