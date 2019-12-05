package com.chen.manager.viewmodel;

import com.chen.manager.enumbean.Gender;

/**
 * 管理员分页Model
 * 
 * created at 2019-12-05
 * 
 * @author Administrator
 *
 */
public class AdminPageVO {

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
	 * 角色
	 */
	private String roleStr;
	
	/**
	 * 性别
	 */
	private Gender gender;
	
	/**
	 * 性别
	 */
	private String genderStr;

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
	 * 是否可用
	 */
	private Boolean enabled;
	
	/**
	 * 是否可用
	 */
	private String enabledStr;

	/**
	 * 备注
	 */
	private String remarks;

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
	
	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getGenderStr() {
		if(gender == null){
			genderStr ="";
		}else{
			genderStr = gender.getDesc();
		}
		return genderStr;
	}

	public void setGenderStr(String genderStr) {
		this.genderStr = genderStr;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEnabledStr() {
		if(enabled == null || enabled == false){
			enabledStr = "禁用";
		}else{
			enabledStr ="启用";
		}
		return enabledStr;
	}

	public void setEnabledStr(String enabledStr) {
		this.enabledStr = enabledStr;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
