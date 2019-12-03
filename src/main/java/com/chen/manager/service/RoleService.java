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

	Page<Role> pageRole(String keyword, PageRequest pageRequest);

	Role insertRole(Role role);

	Role editRole(Role role);

	CommonResult deleteRole(List<Long> longIds);

}
