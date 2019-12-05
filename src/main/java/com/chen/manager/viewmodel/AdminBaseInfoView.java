package com.chen.manager.viewmodel;

import com.chen.manager.entity.Admin;
import com.chen.manager.enumbean.Gender;

/**
 * 管理员基本信息展示类
 * 
 * created at 2019-11-12
 * 
 * @author Administrator
 *
 */
public class AdminBaseInfoView {

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 昵称
	 */
	private String name;

	/**
	 * 性别
	 */
	private Gender gender;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 备注
	 */
	private String remarks;

	public AdminBaseInfoView(Admin admin) {
		this.id = admin.getId();
		this.userName = admin.getUserName();
		this.name = admin.getName();
		this.avatar = admin.getAvatar();
		this.gender = admin.getGender();
		this.phone = admin.getPhone();
		this.email = admin.getEmail();
		this.remarks = admin.getRemarks();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
