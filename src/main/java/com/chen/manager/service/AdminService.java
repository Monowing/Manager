package com.chen.manager.service;

import com.chen.manager.entity.Admin;
import com.chen.manager.viewmodel.AdminBaseInfoView;
import com.chen.manager.viewmodel.CommonResult;

/**
 * Service层——系统管理员
 * 
 * created at 2019-10-31
 * 
 * @author MonoWing
 *
 */
public interface AdminService extends BaseService<Admin, Long> {

	/**
	 * 系统管理员登录
	 * 
	 * @param userName
	 *            用户名
	 * @param passWord
	 *            密码
	 * @return CommonResult 登录结果
	 */
	CommonResult loginWeb(String userName, String passWord);

	/**
	 * 系统管理员登出
	 * 
	 * @param token
	 *            Token
	 */
	void logoutWeb(String token);

	AdminBaseInfoView getAdminBaseInfoView(String token);

	Admin insertAdmin(Admin admin);
}
