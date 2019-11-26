package com.chen.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chen.manager.viewmodel.CommonResult;

/**
 * 系统——角色
 * 
 * created at 2019-11-05
 * 
 * @author MonoWing
 *
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

	
	
	@GetMapping("/index")
	public String index(){
		return "admin/role/index";
	}
	
	@PostMapping("/page")
	public CommonResult getPageData(Integer page,Integer limit){
		System.out.println(page+ " " + limit);
		return new CommonResult().success();
	}
}
