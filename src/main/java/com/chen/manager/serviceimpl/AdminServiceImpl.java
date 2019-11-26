package com.chen.manager.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.chen.manager.dao.AdminDao;
import com.chen.manager.entity.Admin;
import com.chen.manager.service.AdminService;
import com.chen.manager.utils.MD5Helper;
import com.chen.manager.utils.TokenHelper;
import com.chen.manager.viewmodel.AdminBaseInfoView;
import com.chen.manager.viewmodel.CommonResult;

/**
 * ServiceImpl层——系统管理员
 * 
 * created at 2019-10-31
 * 
 * @author MonoWing
 *
 */

@Transactional
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long> implements
		AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	public void setBaseDao(AdminDao adminDao) {
		super.setBaseDao(adminDao);
	}

	@Override
	public CommonResult loginWeb(String userName, String passWord) {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
			return new CommonResult().error("登录失败，用户名或者密码为空");
		}
		List<Admin> list = adminDao.findByUserName(userName);
		if (list == null || list.size() <= 0) {
			return new CommonResult().error("登录失败,用户名或密码错误");
		}
		if (list.size() > 1) {
			// TODO 记录日志——账号有两个以上，存在异常
			return new CommonResult().error("登录失败，账号信息异常");
		}
		Admin admin = list.get(0);
		if (admin == null || admin.getId() <= 0) {
			return new CommonResult().error("登录失败,用户名或密码错误");
		}
		if (MD5Helper.stringMD5(passWord).equals(admin.getPassWord())) {
			String adminToken = modifyToken(admin);
			return new CommonResult().success(adminToken);
		}
		return new CommonResult().error("登录失败,用户名或密码错误");

	}

	@Override
	public void logoutWeb(String token) {
		
		Long adminId = TokenHelper.getLongAdminId(token);
		if(adminId == null || adminId <=0){
			return;
		}
		
		Admin admin = get(adminId).get();
		if (admin == null) {
			return;
		}

		if (token.equals(admin.getToken())) {
			modifyToken(admin);
		}
	}

	/**
	 * 修改Token
	 * 
	 * @param admin
	 *            系统管理员
	 * @return 修改后的Token
	 */
	private String modifyToken(Admin admin) {
		Date date = new Date();
		String adminToken = TokenHelper.generateToken(admin.getId().toString(),
				date.toString());
		admin.setToken(adminToken);
		admin.setTokenDate(date);
		save(admin);
		return adminToken;
	}

	@Override
	public Admin insertAdmin(Admin admin) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public AdminBaseInfoView getAdminBaseInfoView(String token) {
		Long adminId = TokenHelper.getLongAdminId(token);
		if(adminId == null || adminId <=0){
			return null;
		}
		Admin admin = get(adminId).get();
		if (admin == null) {
			return null;
		}
		return new AdminBaseInfoView(admin);
	}

	/*
	 * private Admin findAdminByUserName(String userName) { List<Admin> list =
	 * adminDao.findByUserName(userName); if (list != null && list.size() == 1)
	 * { return list.get(0); } return null; }
	 */
}
