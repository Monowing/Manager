package com.chen.manager.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.chen.manager.dao.AdminDao;
import com.chen.manager.entity.Admin;
import com.chen.manager.service.AdminService;
import com.chen.manager.utils.MD5Helper;
import com.chen.manager.utils.TokenHelper;
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
		if (adminId == null || adminId <= 0) {
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
	public Admin getByToken(String token) {
		Long adminId = TokenHelper.getLongAdminId(token);
		if (adminId == null || adminId <= 0) {
			return null;
		}
		return get(adminId).get();
	}

	@Override
	public Page<Admin> pageAdmin(String keyword, PageRequest pageRequest) {
		return adminDao.pageRole(fieldLike(keyword), pageRequest);
	}

	@Override
	public Admin insertAdmin(Admin admin) {
		List<Admin> adminList = adminDao.findByUserName(admin.getUserName());
		if (adminList != null && adminList.size() > 0) {
			return null;
		}

		admin.setEnabled(true);
		admin.setPassWord(MD5Helper.stringMD5(admin.getPassWord()));

		return save(admin);
	}

	@Override
	public Admin editAdmin(Admin admin) {
		Long id = admin.getId();
		Admin original = get(id).get();
		System.out.println("original " + original.toString());
		System.out.println("now " + admin.toString());

		BeanUtils.copyProperties(original, admin, "role", "name", "gender",
				"avatar", "phone", "email", "enabled", "remarks");

		return save(admin);
	}

	@Override
	public CommonResult deleteAdmin(List<Long> ids) {
		CommonResult result = new CommonResult();

		if (ids == null || ids.size() <= 0) {
			return result.error("数据为空，无法删除！");
		}

		for (Long id : ids) {
			if (id == null || id <= 0) {
				continue;
			}
			Admin admin = get(id).get();
			if (admin == null || admin.getRole() == null) {
				continue;
			}

			if (admin.getRole().getInternalSign()) {
				return result.error("数据包含系统角色，不可删除！");
			}

		}

		for (Long id : ids) {
			delete(id);
		}

		return result.success();
	}

	@Override
	public void editMine(Admin admin) {

		Long id = admin.getId();
		Admin original = get(id).get();
		System.out.println("original " + original.toString());
		System.out.println("now " + admin.toString());

		BeanUtils.copyProperties(original, admin, "name", "gender", "avatar",
				"phone", "email", "remarks");

		save(admin);

	}

	@Override
	public CommonResult changePassword(String orgpsd, String newpsd,
			String confirmpsd, String token) {

		if (StringUtils.isEmpty(orgpsd) || StringUtils.isEmpty(newpsd)
				|| StringUtils.isEmpty(confirmpsd)
				|| StringUtils.isEmpty(token)) {
			return new CommonResult().error();
		}

		Admin admin = getByToken(token);

		if (admin == null) {
			return new CommonResult().error("该用户不存在！");
		}

		if (MD5Helper.stringMD5(orgpsd).equals(admin.getPassWord())
				&& newpsd.equals(confirmpsd)) {

			admin.setPassWord(MD5Helper.stringMD5(newpsd));
			save(admin);

			return new CommonResult().success(null, "密码修改成功！");

		}

		return new CommonResult().error("原密码错误，请重新输入原密码！");
	}

}
