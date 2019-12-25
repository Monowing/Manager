package com.chen.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.chen.manager.entity.Role;
import com.chen.manager.viewmodel.CommonResult;

/**
 * 角色Service
 * 
 * created at 2019-11-22
 * 
 * @author MonoWing
 *
 */
public interface RoleService extends BaseService<Role, Long> {

	/**
	 * 新增角色
	 * 
	 * @param role
	 *            角色信息
	 * @return
	 */
	Role insertRole(Role role);

	/**
	 * 编辑角色
	 * 
	 * @param role
	 *            角色信息
	 * @return
	 */
	Role editRole(Role role);

	/**
	 * 删除角色
	 * @param ids 角色ID的list
	 * @return
	 */
	CommonResult deleteRole(List<Long> ids);

	/**
	 * 获取角色分页数据
	 * 
	 * @param keyword
	 *            搜索关键字
	 * @param pageRequest
	 *            分页信息
	 * @return
	 */
	Page<Role> pageRole(String keyword, PageRequest pageRequest);
}
