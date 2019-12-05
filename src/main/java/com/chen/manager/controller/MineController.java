package com.chen.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.manager.entity.Admin;
import com.chen.manager.enumbean.Gender;
import com.chen.manager.service.AdminService;
import com.chen.manager.viewmodel.CommonResult;

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

	@Autowired
	private AdminService adminService;

	/**
	 * 页面跳转——基本资料页面
	 * 
	 * @return
	 */
	@GetMapping("/baseInfo")
	public String baseInfo(ModelMap model) {
		model.addAttribute("genderList", Gender.values());
		return "admin/mine/baseInfo";
	}

	/**
	 * 页面跳转——修改密码页面
	 * 
	 * @return
	 */
	@GetMapping("/changePsd")
	public String changePsd(ModelMap model) {
		return "/admin/mine/changePsd";
	}

	/**
	 * 设置我的资料
	 * 
	 * @param admin
	 * @return
	 */
	@PostMapping("/edit")
	@ResponseBody
	public CommonResult edit(Admin admin) {
		System.out.println(admin.toString());
		adminService.editMine(admin);
		return new CommonResult().success();
	}

	@PostMapping("/psdChange")
	@ResponseBody
	public CommonResult psdChange(String userName,String orgpsd, String newpsd,
			String confirmpsd, String token) {
		System.out.println(orgpsd + " "+ newpsd + " "+ confirmpsd + " "+ token);
		return adminService.changePassword(orgpsd,newpsd,confirmpsd,token);
	}
}
