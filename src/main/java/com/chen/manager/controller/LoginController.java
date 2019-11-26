package com.chen.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.manager.service.AdminService;
import com.chen.manager.viewmodel.CommonResult;

@Controller
public class LoginController {

	@Autowired
	private AdminService adminService;

	/**
	 * 登录页
	 * @return
	 */
	@RequestMapping("/login")
	private String login(){
		return "admin/base/login";
	}
	
	/**
	 * 登录
	 * 
	 * @param userName
	 *            用户名
	 * @param passWord
	 *            密码
	 * @param request
	 * @return
	 */
	@PostMapping("/loginWeb")
	@ResponseBody
	public CommonResult login(String userName, String passWord) {
		return adminService.loginWeb(userName, passWord);
	}

	/**
	 * 登出
	 * 
	 * @param token
	 *            Token值
	 * @return
	 */
	@GetMapping("/logoutWeb")
	public String logout(String token) {
		adminService.logoutWeb(token);
		return "redirect:login";
	}

}
