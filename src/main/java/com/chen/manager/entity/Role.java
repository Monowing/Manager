package com.chen.manager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统表——管理员角色表
 * 
 * created at 2019-10-31
 * 
 * @author MonoWing
 *
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2938337714508750771L;

	/**
	 * 管理员角色名称
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 获取管理员角色名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置管理员角色名称
	 * 
	 * @param name
	 *            角色名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取描述
	 * 
	 * @return
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param desc
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}