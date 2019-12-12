package com.chen.manager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chen.manager.viewmodel.CommonResult;

/**
 * 系统——基础
 * 
 * created at 2019-11-01
 * 
 * @author MonoWing
 *
 */
@Controller
public class BaseController {

	/**
	 * 页面跳转——导航页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	private String index() {
		return "/admin/base/index";
	}

	/**
	 * 页面跳转——首页
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	private String main() {
		return "/admin/base/main";
	}

}
